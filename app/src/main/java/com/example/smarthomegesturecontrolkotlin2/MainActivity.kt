package com.example.smarthomegesturecontrolkotlin2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val dropDownList = arrayOf(
                "Select Gesture",
                "LightOn",
                "LightOff",
                "FanOn",
                "FanOff",
                "FanUp",
                "FanDown",
                "SetThermo",
                "Num0",
                "Num1",
                "Num2",
                "Num3",
                "Num4",
                "Num5",
                "Num6",
                "Num7",
                "Num8",
                "Num9"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(spinner.selectedItemPosition == 1) {
                    condition1(spinner)
                }
                if(spinner.selectedItemPosition == 2) {
                    condition2(spinner)
                }
                if(spinner.selectedItemPosition == 3) {
                    condition3(spinner)
                }
                if(spinner.selectedItemPosition == 4) {
                    condition4(spinner)
                }
                if(spinner.selectedItemPosition == 5) {
                    condition5(spinner)
                }
                if(spinner.selectedItemPosition == 6) {
                    condition6(spinner)
                }
                if(spinner.selectedItemPosition == 7) {
                    condition7(spinner)
                }
                if(spinner.selectedItemPosition == 8) {
                    condition8(spinner)
                }
                if(spinner.selectedItemPosition == 9) {
                    condition9(spinner)
                }
                if(spinner.selectedItemPosition == 10) {
                    condition10(spinner)
                }
                if(spinner.selectedItemPosition == 11) {
                    condition11(spinner)
                }
                if(spinner.selectedItemPosition == 12) {
                    condition12(spinner)
                }
                if(spinner.selectedItemPosition == 13) {
                    condition13(spinner)
                }
                if(spinner.selectedItemPosition == 14) {
                    condition14(spinner)
                }
                if(spinner.selectedItemPosition == 15) {
                    condition15(spinner)
                }
                if(spinner.selectedItemPosition == 16) {
                    condition16(spinner)
                }
                if(spinner.selectedItemPosition == 17) {
                    condition17(spinner)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }

        }


    }


    private fun condition1(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "LightOn")
        startActivity(intent)
    }

    private fun condition2(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "LightOff")
        startActivity(intent)
    }

    private fun condition3(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "FanOn")
        startActivity(intent)
    }

    private fun condition4(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "FanOff")
        startActivity(intent)
    }

    private fun condition5(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "FanUp")
        startActivity(intent)
    }

    private fun condition6(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "FanDown")
        startActivity(intent)
    }

    private fun condition7(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "SetThermo")
        startActivity(intent)
    }

    private fun condition8(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num0")
        startActivity(intent)
    }

    private fun condition9(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num1")
        startActivity(intent)
    }

    private fun condition10(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num2")
        startActivity(intent)
    }

    private fun condition11(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num3")
        startActivity(intent)
    }

    private fun condition12(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num4")
        startActivity(intent)
    }

    private fun condition13(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num5")
        startActivity(intent)
    }

    private fun condition14(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num6")
        startActivity(intent)
    }

    private fun condition15(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num7")
        startActivity(intent)
    }

    private fun condition16(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num8")
        startActivity(intent)
    }

    private fun condition17(spinner: Spinner) {
        val intent = Intent(this, ActivityTurnOnLights1::class.java)
        intent.putExtra("ziba", "Num9")
        startActivity(intent)
    }






}