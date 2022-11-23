package com.example.homework5_1

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework5_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    private val dataList: ArrayList<Data> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // onBindViewHolder 에서 listposition 을 전달받고 함수 실행
        val dataRVAdapter = DataRVAdapter(dataList, onClickDeleteBtn = {
            deleteTask(it) // deleteTask 함수가 포지션 값인 it을 받고 지운다.
        })

        // ActivityResult 를 받기 위한 Callback 등록
        getResultText =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val mString = result.data?.getStringExtra("data") ?: ""
                    Log.d(TAG, "onCreate: $mString")

                    dataList.apply {
                        add(Data("$mString"))
                    }
                    dataRVAdapter.notifyItemRangeInserted(dataList.size, 1) // 변경된 아이템의 시작 위치, 변경된 아이템 개수
                }
            }
        // 메모 추가 버튼 누르면 메모 화면으로 넘어가는 것 && 위에서 정의한 것을 launch 함수로 시작
        binding.btnAdd.setOnClickListener {
            val mintent = Intent(this@MainActivity, MemoActivity::class.java)
            getResultText.launch(mintent)
        }

        binding.rvData.adapter = dataRVAdapter  // 리사이클러뷰에 어댑터 연결
        binding.rvData.layoutManager = LinearLayoutManager(this) // 레이아웃 매니저 연결

    }
    fun deleteTask(data: Data) {
        dataList.remove(data)
        binding.rvData.adapter?.notifyDataSetChanged()
    }
}