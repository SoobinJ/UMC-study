package com.example.homework8

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase //import 해줘야 함


@Database(entities = [User::class], version = 1)//데이터베이스와 실제 객체를 넣어줌
abstract class AppDatabase: RoomDatabase() { //abstract 해줘야함 , 공식문서에서 그럼

    abstract fun userDao():UserDao //userDao를 불러옴

    companion object{ //전역적으로 사용가능, 데이터베이스의 경우 계속 생성하는 것이 아니라 재활용하기때문에
        private var appDatabase:AppDatabase?=null //변수에 직접 접근할 수 없게 하기 위해 private 사용, null 값 허용(함수를 통해서 나중에 초기화 작업 진행함), 데이터베이스가 실제로 담기는 변수

        @Synchronized //여러 쓰레드에서 하나의 자원에 접근하려고 할 때 방지하기 위해 사용, 사용하고 있음을 보여주는 느낌
        fun getInstance(context: Context): AppDatabase? { //instance를 가져오는 함수, 꼭 Context를 가져와야함
            if (appDatabase == null) { //null인지 아닌지 확인 null인 경우 초기화 필요
                synchronized(AppDatabase::class.java) { //이 클래스가 점유를 하고 있음을 보여줌
                    appDatabase=Room.databaseBuilder(//roomdb 생성
                        context.applicationContext, //전역적으로 사용하기 위해서 .applicationContext 사용
                        AppDatabase::class.java, //지금 room database 객체 자체
                        "app-database"//데이터베이스 이름, 겹치면 안됨
                    ).allowMainThreadQueries().build() //메인 thread에서 쿼리 허용
                }

            }
            return appDatabase
        }
    }
}