package com.example.homework8

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.homework8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val roomDb=AppDatabase.getInstance(this)

        if(roomDb!=null){
//            val user=User("숩숩",23) //객체 생성, userId의 경우 자동으로 생성
//            roomDb.userDao().insert(user) //roomdb에 user가 들어감

            //roomDb.userDao().updateNameByUserId(1,"전수빈") //id값을 이용해 name 변경

//            val deleteUser=User("",0,1) //delete
//            roomDb.userDao().delete(deleteUser)

//            val userList=roomDb.userDao().selectAll() //모든 정보 가져오기
//            Log.d("DB","User List: ${userList}")

            val user=roomDb.userDao().selectByUserId(2)
            Log.d("DB","User Id 2 :$user")
        }
    }

}