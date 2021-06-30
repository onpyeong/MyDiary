package com.example.mydiary

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiary(diaryData: DiaryData)

    @Delete
    fun deleteDiary(diaryData: DiaryData)

    @Query("SELECT * From diary_data")
    fun getAll(): LiveData<List<DiaryData>> //List
}