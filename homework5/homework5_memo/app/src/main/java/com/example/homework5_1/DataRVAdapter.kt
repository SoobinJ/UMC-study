package com.example.homework5_1

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework5_1.databinding.ItemDataBinding
import java.util.Calendar.getInstance

// Adapter 인자에 데이터를 넣어주면, MainActivity 에서 수정하면 자동으로 수정됨
class DataRVAdapter(
    private val dataList: ArrayList<Data>,
    val onClickDeleteBtn: (data: Data) -> Unit):
    RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {
    // ViewHolder 객체
    inner class DataViewHolder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.tvMemo.text = data.memo;
        }
    }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    // ViewHolder 가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val listposition = dataList[position]
        holder.bind(listposition)
        // deleteBtn 이 눌렸을 때 position 전달
        holder.binding.deleteBtn.setOnClickListener {
            onClickDeleteBtn.invoke(listposition)
        }
    }

    // 표현할 Item 의 총 개수
    override fun getItemCount(): Int = dataList.size
}