package com.example.homework8_memo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8_memo.databinding.ItemDataBinding

class MemoRVAdapter(
    private val dataList: ArrayList<Memo>):
    RecyclerView.Adapter<MemoRVAdapter.DataViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(position: Int)
    }

    interface ItemDelClickListener{
        fun onItemDelClick(position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    private lateinit var itemDelClickListener: ItemDelClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener=itemClickListener
    }

    fun setItemDelClickListener(itemDelClickListener: ItemDelClickListener) {
        this.itemDelClickListener=itemDelClickListener
    }

    inner class DataViewHolder(val binding: ItemDataBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(data: Memo){
                binding.tvMemo.text=data.memo
            }
        }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return DataViewHolder(binding)
    }

    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])

        holder.binding.checkBtn.setOnClickListener{
            itemClickListener.onItemClick(position)
        }

        holder.binding.deleteBtn.setOnClickListener{
            itemDelClickListener.onItemDelClick(position)
        }
    }





    // 표현할 Item 의 총 개수
    override fun getItemCount(): Int = dataList.size
}