package com.example.mydiary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.databinding.ItemDiaryBinding

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.DiaryItemViewHolder>() {

    val diaryList = mutableListOf<DiaryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryItemViewHolder {
        val binding = ItemDiaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DiaryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryItemViewHolder, position: Int) {
        holder.onBind(diaryList[position])
    }

    override fun getItemCount(): Int = diaryList.size

    class DiaryItemViewHolder(
        private val binding: ItemDiaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(diaryItem: DiaryData) {
            with(binding){
                txtTitle.text = diaryItem.title
                txtContent.text = diaryItem.content
            }
        }
    }

}