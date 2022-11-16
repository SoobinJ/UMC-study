package com.example.homework3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var viewBinding:ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras=intent.extras
        var data = extras!!["text"] as String

        viewBinding.secontText.text=data

        viewBinding.nextBtn.setOnClickListener{
            var intent= Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}