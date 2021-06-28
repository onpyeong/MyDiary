package com.example.mydiary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiaryData::class], version = 1) //{}
abstract class DiaryDatabase: RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object {
        private var singleDiaryDatabase: DiaryDatabase? = null

        // 싱글턴 패턴
        fun getInstance(context: Context): DiaryDatabase? {
            if(singleDiaryDatabase == null) {
                synchronized(DiaryDatabase::class) { // 여러 스레드가 접근 못하도록
                    singleDiaryDatabase = Room.databaseBuilder(context.applicationContext,
                        DiaryDatabase::class.java, "diary_data")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return singleDiaryDatabase
        }
    }
}