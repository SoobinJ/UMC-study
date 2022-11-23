package com.example.homework8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //roomdb에서 사용되는 객체임을 의미
data class User(
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="age") val age:Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="userId") val userId:Int=0
    //고유한 키, 관계형 데이터베이스에 존재, autoGenerate : 자동으로 타입에 따라 증가
)
