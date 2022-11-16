package com.example.homework6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.containerFragment.id, HomeFragment())
            .commitAllowingStateLoss()

        binding.navBottom.run {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_home ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_setting ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, SettingFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_alarm ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, AlarmFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId=R.id.menu_home
        }
    }
}