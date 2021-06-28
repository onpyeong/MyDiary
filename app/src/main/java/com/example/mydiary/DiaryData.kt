package com.example.mydiary

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_data")
data class DiaryData(
    @PrimaryKey

    var title: String,
    var content: String
)
