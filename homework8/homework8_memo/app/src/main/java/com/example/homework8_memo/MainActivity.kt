package com.example.homework8_memo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework8_memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    private val dataList: ArrayList<Memo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val roomDb=AppDatabase.getInstance(this)
        val memoRVAdapter=MemoRVAdapter(dataList)

        if(roomDb!=null){
            val memo=roomDb.memoDao().selectAll()
            dataList.addAll(memo)
        }

        memoRVAdapter.setItemDelClickListener( object : MemoRVAdapter.ItemDelClickListener{
            override fun onItemDelClick(position: Int) {
                Log.d("position","$position")
                val temp = dataList[position]
                if(roomDb!=null){
                roomDb.memoDao().delete(temp);
            }
                deleteTask(temp)
            }
        })

        binding.rvData.adapter=memoRVAdapter
        binding.rvData.layoutManager=LinearLayoutManager(this)

        getResultText =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    if(roomDb!=null){
                        val str = result.data?.getStringExtra("data") ?: ""
                        val memo=Memo("${str}")
                        roomDb.memoDao().insert(memo)
                        dataList.apply {
                            add(Memo("$str"))
                        }
                        memoRVAdapter.notifyItemRangeInserted(dataList.size, 1) // 변경된 아이템의 시작 위치, 변경된 아이템 개수
                    }
                }
            }

        binding.btnAdd.setOnClickListener {
            val mintent = Intent(this@MainActivity, MemoActivity::class.java)
            getResultText.launch(mintent)
        }

        val sharedPrefs = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()


        memoRVAdapter.setItemClickListener( object : MemoRVAdapter.ItemClickListener{
            override fun onItemClick(position: Int) {

                val temp = dataList[position].memo

                editor.putString("memo","$temp")
                editor.apply()
            }
        })

        binding.btnSaved.setOnClickListener {
            val mintent = Intent(this@MainActivity, StoredActivity::class.java)
            getResultText.launch(mintent)
        }

    }
    fun deleteTask(data: Memo) {
        dataList.remove(data)
        binding.rvData.adapter?.notifyDataSetChanged()
    }
}