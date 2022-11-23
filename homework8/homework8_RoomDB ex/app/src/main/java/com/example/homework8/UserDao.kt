package com.example.homework8

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao { //유저 객체를 데이터베이스에서 다룰 수 있는 dao 파일, 실질적으로 데이터를 가져오고 지우고 등 작성
    @Insert
    fun insert(user:User)

    @Delete
    fun delete(user:User)

    @Query("SELECT * FROM User") //쿼리문 직접 작성, User 테이블에 있는 모든 정보를 가져옴
    fun selectAll():List<User> //리턴값, 값이 여러개일수도 있기 때문에 List로

    @Query("SELECT * FROM User WHERE userId = :userId") //특정 정보 가져오기, WHERE : 조건
    fun selectByUserId(userId: Int):User //입력된 userId로 찾음

    @Query("SELECT * FROM User WHERE name =:name")
    fun selectByName(name: String):List<User> //이름으로 정보 가져오기

    @Query("UPDATE User SET name = :name WHERE userID= :userId")
    fun updateNameByUserId(userId: Int,name:String)  //userId로 name 업데이트
}