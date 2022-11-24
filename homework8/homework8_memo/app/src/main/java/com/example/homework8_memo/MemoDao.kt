package com.example.homework8_memo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {
    @Insert
    fun insert(memo:Memo)

    @Delete
    fun delete(memo:Memo)

    @Query("SELECT * FROM Memo")
    fun selectAll():List<Memo>
}