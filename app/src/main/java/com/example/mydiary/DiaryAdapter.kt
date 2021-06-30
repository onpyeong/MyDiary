package com.example.mydiary

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.databinding.ItemDiaryBinding

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.DiaryItemViewHolder>() {

    // 고차함수
    private var deleteButtonClickListener : ((DiaryData) -> Unit)? = null

    // MainActivity에서 구현된 로직으로 set
    fun setDeleteButtonClickListener(listener : (DiaryData) -> Unit) {
        deleteButtonClickListener = listener
    }

    var diaryList = mutableListOf<DiaryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryItemViewHolder {
        val binding = ItemDiaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DiaryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryItemViewHolder, position: Int) {
        holder.onBind(diaryList[position])
        holder.binding.btnClear.setOnClickListener {
            Log.d("button", "click")
            // MainActivity에서의 로직으로 set된 listener를 invoke 시켜서
            // 현재 선택된 item을 delete함
            deleteButtonClickListener?.invoke(diaryList[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = diaryList.size

    class DiaryItemViewHolder(
        val binding: ItemDiaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(diaryItem: DiaryData) {
            with(binding){
                txtTitle.text = diaryItem.title
                txtContent.text = diaryItem.content
            }
        }
    }

}