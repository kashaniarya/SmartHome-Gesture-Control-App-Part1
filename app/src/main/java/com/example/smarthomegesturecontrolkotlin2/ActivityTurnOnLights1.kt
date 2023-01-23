package com.example.smarthomegesturecontrolkotlin2

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class ActivityTurnOnLights1 : AppCompatActivity() {

    // declaring a null variable for VideoView
    var simpleVideoView: VideoView? = null

    // declaring a null variable for MediaController
    private var mediaControls: MediaController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn_on_lights1)

        // get reference to button
        val btnBack = findViewById<Button>(R.id.buttonBack)
        // set on-click listener
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnPractice = findViewById<Button>(R.id.buttonPractice)
        btnPractice.setOnClickListener {
            val intent = Intent(this, ActivityTurnOnLights2::class.java)
            startActivity(intent)
        }

        // assigning id of VideoView from
        // activity_main.xml layout file
        simpleVideoView = findViewById<View>(R.id.videoView) as VideoView

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }

        // set the media controller for video view
        simpleVideoView!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        simpleVideoView!!.setVideoURI(
            Uri.parse("android.resource://"
                + packageName + "/" + R.raw.lighton))
        //  videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.lighton);

        simpleVideoView!!.requestFocus()

        // starting the video
        simpleVideoView!!.start()

        simpleVideoView!!.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })


    }




}