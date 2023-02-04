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

        val gestureName = intent.getStringExtra("ziba") ?: "no data"

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
            intent.putExtra("gestureName", gestureName)
            startActivity(intent)
        }

        // assigning id of VideoView from
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
        if (gestureName == "LightOn") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.lighton))
        }
        if (gestureName == "LightOff") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.lightoff))
        }
        if (gestureName == "FanOn") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.fanon))
        }
        if (gestureName == "FanOff") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.fanoff))
        }
        if (gestureName == "FanUp") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.increasefanspeed))
        }
        if (gestureName == "FanDown") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.decreasefanspeed))
        }
        if (gestureName == "SetThermo") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.setthermo))
        }
        if (gestureName == "Num0") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num0))
        }
        if (gestureName == "Num1") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num1))
        }
        if (gestureName == "Num2") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num2))
        }
        if (gestureName == "Num3") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num3))
        }
        if (gestureName == "Num4") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num4))
        }
        if (gestureName == "Num5") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num5))
        }
        if (gestureName == "Num6") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num6))
        }
        if (gestureName == "Num7") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num7))
        }
        if (gestureName == "Num8") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num8))
        }
        if (gestureName == "Num9") {
            simpleVideoView!!.setVideoURI(
                Uri.parse("android.resource://"
                        + packageName + "/" + R.raw.num9))
        }


        simpleVideoView!!.requestFocus()

        // starting the video
        simpleVideoView!!.start()

        simpleVideoView!!.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
            mp.isLooping = true
        })

    }



}