package com.example.mydiary

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors

//singleton
object DiaryRepositoryImpl : DiaryRepository {
    private val application = App.instance.applicationContext
    private val diaryDatabase = DiaryDatabase.getInstance(application)!!
    private val diaryDao: DiaryDao = diaryDatabase.diaryDao()
    private val diaries: LiveData<List<DiaryData>> = diaryDao.getAll()

    override fun getAll(): LiveData<List<DiaryData>> {
        return diaries
    }

    override fun insert(diaryData: DiaryData) {
        CoroutineScope(Dispatchers.IO).launch { // 다른애한테 일 시키기
            diaryDao.insertDiary(diaryData)
        }
    }

    override fun delete(diaryData: DiaryData) {
        CoroutineScope(Dispatchers.IO).launch { // 다른애한테 일 시키기
            diaryDao.deleteDiary(diaryData)
        }
    }

}