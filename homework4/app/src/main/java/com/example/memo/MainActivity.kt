package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  var memo:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        var view=binding.root
        setContentView(view)

        binding.btnMain.setOnClickListener{
            var intent=Intent(this,MainActivity2::class.java)
            intent.putExtra("data",binding.editMain.text.toString())
            startActivity(intent)
        }

    }

    override fun onStop() {
        super.onStop()
        memo=binding.editMain.text.toString()
        binding.editMain.text.clear()

    }

    override fun onRestart() {
        super.onRestart()
        AlertDialog.Builder(this)
            .setTitle("알림")
            .setMessage("이어서 작성?")
            .setPositiveButton("Yes"){dialog,which->
                binding.editMain.setText(memo)
            }
            .setNegativeButton("no"){dialog,which->
                binding.editMain.text.clear()
            }
            .create()
            .show()
    }

}