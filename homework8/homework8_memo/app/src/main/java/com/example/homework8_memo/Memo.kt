package com.example.homework8_memo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class Memo(
    @ColumnInfo(name="memo") val memo: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id:Int=0
)
