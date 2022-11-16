package com.example.homework7

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time=0
    private var timerTask:Timer?=null
    private var isRunning =false

    lateinit var secTextView: TextView
    lateinit var timerSettingButton:Button
    lateinit var inputEditText:EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        secTextView=findViewById<TextView>(R.id.secTextView)
        timerSettingButton=findViewById<Button>(R.id.timerSettingButton)
        inputEditText=findViewById<EditText>(R.id.inputEditText)

        timerSettingButton.setOnClickListener{
            if(inputEditText.text.toString().toInt()!=0){
                time=inputEditText.text.toString().toInt()*100
                timerTask=timer(period=10){
                    time--
                    var sec=time/100
                    runOnUiThread{
                        secTextView.text="$sec"
                    }
                    if(time==0){
                        runOnUiThread{
                            secTextView.text="0"
                            timerTask?.cancel()
                        }
                    }

                }
            }
        }
    }
}