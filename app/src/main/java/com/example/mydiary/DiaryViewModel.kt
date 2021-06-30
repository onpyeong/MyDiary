package com.example.mydiary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DiaryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DiaryRepositoryImpl
    private val diaries = repository.getAll()

    fun getAll(): LiveData<List<DiaryData>> {
        return this.diaries
    }

    fun insert(diaryData: DiaryData) {
        repository.insert(diaryData)
    }

    fun delete(diaryData: DiaryData) {
        repository.delete(diaryData)
    }

}