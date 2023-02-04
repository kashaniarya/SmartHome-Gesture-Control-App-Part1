package com.example.smarthomegesturecontrolkotlin2

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.smarthomegesturecontrolkotlin2.databinding.ActivityTurnOnLights3Binding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import java.io.InputStream

class ActivityTurnOnLights3 : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTurnOnLights3Binding
    private val url = "http://" + "10.0.2.2" + ":" + 5000 + "/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTurnOnLights3Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_turn_on_lights3)

        val akstr = intent.getStringExtra("arya") ?: "no data"
        val gestureName = intent.getStringExtra("gestureName") ?: "no data"

        val videoView = findViewById<VideoView>(R.id.videoView3)
        videoView.setVideoURI(akstr.toUri())
        videoView.start()
        videoView!!.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            try {
                var uri = akstr.toUri()
                val inputStream: InputStream? = contentResolver.openInputStream(uri)
                val file: File = File.createTempFile(gestureName, ".mp4")
                inputStream.use { input ->
                    file.outputStream().use { output ->
                        input!!.copyTo(output)
                    }
                }
                postRequest(url + "video", file)
            }
            catch (e: Exception) {
                Toast.makeText(this@ActivityTurnOnLights3, "File uploading failed in catch: " + e.message, Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonRetake = findViewById<Button>(R.id.buttonRetake)
        buttonRetake.setOnClickListener {
            val intent = Intent(this, ActivityTurnOnLights2::class.java)
            startActivity(intent)
        }
    }


    private fun getMimeType(file: File): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    private fun postRequest(URL: String, sourceFile: File) {
        val fileName: String = sourceFile.name
        val mimeType = getMimeType(sourceFile)
        val requestBody: RequestBody =
                    MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("uploaded_file", fileName,sourceFile.asRequestBody(mimeType?.toMediaTypeOrNull()))
                        .build()

        val request: Request = Request.Builder()
            .post(requestBody)
            .url(URL)
            .build()

        val okHttpClient = OkHttpClient()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@ActivityTurnOnLights3, "Something went wrong:" + " " + e.message, Toast.LENGTH_LONG).show()
                    call.cancel()
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    try {
                        Toast.makeText(this@ActivityTurnOnLights3, response.body!!.string(), Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

}