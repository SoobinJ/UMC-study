package com.example.homework8_memo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework8_memo.databinding.ActivityStoredBinding

class StoredActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityStoredBinding
    private val memoList: ArrayList<Memo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityStoredBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val memoRVAdapter= MemoRVAdapter(memoList)

        val shared=getSharedPreferences("sharedprefs",Context.MODE_PRIVATE)
        val memo = shared.getString("memo","").toString()

        memoList.add(Memo(memo))

        viewBinding.rvData.adapter= memoRVAdapter
        viewBinding.rvData.layoutManager=LinearLayoutManager(this)
    }
}