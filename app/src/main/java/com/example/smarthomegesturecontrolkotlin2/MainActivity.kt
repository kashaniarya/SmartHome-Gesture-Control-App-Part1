package com.example.smarthomegesturecontrolkotlin2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTurnOnLights = findViewById<Button>(R.id.buttonTurnOnLights)
        btnTurnOnLights.setOnClickListener {
            val intent = Intent(this, ActivityTurnOnLights1::class.java)
            startActivity(intent)
        }


        val turnOffLightsButton = findViewById<Button>(R.id.buttonTurnOffLights)
        turnOffLightsButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity,"clicked", Toast.LENGTH_SHORT).show()
        })


    }




}