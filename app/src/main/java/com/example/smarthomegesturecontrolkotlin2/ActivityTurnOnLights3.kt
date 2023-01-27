package com.example.smarthomegesturecontrolkotlin2

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.core.net.toUri
import com.example.smarthomegesturecontrolkotlin2.databinding.ActivityTurnOnLights2Binding
import com.example.smarthomegesturecontrolkotlin2.databinding.ActivityTurnOnLights3Binding

class ActivityTurnOnLights3 : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTurnOnLights3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTurnOnLights3Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_turn_on_lights3)


        val akstr = intent.getStringExtra("arya") ?: "no data"

        val videoView = findViewById<VideoView>(R.id.videoView3)
        videoView.setVideoURI(akstr.toUri())
        videoView.start()
        videoView!!.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })


        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            Toast.makeText(baseContext, akstr, Toast.LENGTH_SHORT).show()
            System.out.println(akstr);
        }

        val buttonRetake = findViewById<Button>(R.id.buttonRetake)
        buttonRetake.setOnClickListener {
            val intent = Intent(this, ActivityTurnOnLights2::class.java)
            startActivity(intent)
        }
    }
}