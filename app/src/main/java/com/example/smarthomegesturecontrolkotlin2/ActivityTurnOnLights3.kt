package com.example.smarthomegesturecontrolkotlin2

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
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
    private var xxx = 0
    private val url = "http://" + "10.0.2.2" + ":" + 5000 + "/"
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTurnOnLights3Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_turn_on_lights3)


        val akstr = intent.getStringExtra("arya") ?: "no data"
        //Log.d("arya: ","akstr::: " + akstr)


        val videoView = findViewById<VideoView>(R.id.videoView3)
        videoView.setVideoURI(akstr.toUri())
        videoView.start()
        videoView!!.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })


        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            //Toast.makeText(baseContext, akstr, Toast.LENGTH_SHORT).show()
            xxx += 1

            try {
                var uri = akstr.toUri()
                var path = uri.path

                // Make sure the Pictures directory exists.
                //var  path: File? = this.getExternalFilesDir(Environment.DIRECTORY_MOVIES) //
                //var path = Environment.getExternalStoragePublicDirectory("content://media/external/video/media/")
                //var storageDir = Environment.getExternalStoragePublicDirectory(path)
                //var path = this.getExternalFilesDir(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.path)
                //var slashIndex  = akstr.lastIndexOf('/')
                //var vidName = akstr.substring(slashIndex+1)
                //Log.d("vid name",vidName);
                var storageDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
                //var storageDir = getExternalFilesDir(akstr)

                //var path2 = URIPathHelper.getPath(this, uri)
                //var file = File(path)

                val inputStream: InputStream? = contentResolver.openInputStream(uri)

                var vidName = "TurnOnLights${xxx}"
                val file: File = createTempFile(vidName, ".mp4", storageDir)

                inputStream.use { input ->
                    file.outputStream().use { output ->
                        input!!.copyTo(output)
                    }
                }

                //var file2 = uri.toFile()

//                var file = File.createTempFile("TurnOnLights$xxx",".mp4", path)
                postRequest(akstr, url + "video", file)
            }
            catch (e: Exception) {
                Toast.makeText(this@ActivityTurnOnLights3, "File uploading failed in catch: " + e.message, Toast.LENGTH_SHORT).show()
                println("e-msg: " + e.message);
                Log.d("catch: ", e.message.toString());
            }
            //uploadFile(File(akstr), "TurnOnLight_PRACTICE_$xxx")
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

//    fun uploadFile(sourceFilePath: String, uploadedFileName: String? = null) {
//        uploadFile(File(sourceFilePath), uploadedFileName)
//    }
//
//    fun uploadFile(sourceFileUri: Uri, uploadedFileName: String? = null) {
//        val pathFromUri = URIPathHelper().getPath(this,sourceFileUri)
//        uploadFile(File(pathFromUri), uploadedFileName)
//    }

    private fun uploadFile(sourceFile: File, uploadedFileName: String? = null) {
        Thread {
            val mimeType = getMimeType(sourceFile);
            if (mimeType == null) {
                //Log.e("file error", "Not able to get mime type")
                return@Thread
            }
            val fileName: String = uploadedFileName ?: sourceFile.name
            //toggleProgressDialog(true)
            try {
                val requestBody: RequestBody =
                    MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("uploaded_file", fileName,sourceFile.asRequestBody(mimeType.toMediaTypeOrNull()))
                        .build()

                val request: Request = Request.Builder().url(url).post(requestBody).build()

                val response: Response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    //Log.d("File upload","success, path: $serverUploadDirectoryPath$fileName")
                    Toast.makeText(this@ActivityTurnOnLights3, "File uploading successful", Toast.LENGTH_SHORT).show()
                    //showToast("File uploaded successfully at $serverUploadDirectoryPath$fileName")
                } else {
                    //Log.e("File upload", "failed")
                    Toast.makeText(this@ActivityTurnOnLights3, "File uploading failed", Toast.LENGTH_SHORT).show()
                    //showToast("File uploading failed")
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                //Log.e("File upload", "failed")
                Toast.makeText(this@ActivityTurnOnLights3, "File uploading failed", Toast.LENGTH_SHORT).show()
                //showToast("File uploading failed")
            }
            //toggleProgressDialog(false)
        }.start()
    }

    private fun postRequest(message: String, URL: String, sourceFile: File) {
        //val requestBody = buildRequestBody(message)
        val fileName: String = sourceFile.name
        //val formbody: RequestBody = FormBody.Builder().add("sample", message).build()


        val mimeType = getMimeType(sourceFile)
        //xxx += 1
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
                    System.out.println("e-msg: " + e.message);
                    Log.d("taggg: ", e.message.toString());
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