package com.example.mydiary

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData

interface DiaryRepository {
    fun getAll(): LiveData<List<DiaryData>>
    fun insert(diaryData: DiaryData)
    fun delete(diaryData: DiaryData)
}