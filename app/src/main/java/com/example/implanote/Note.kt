package com.example.implanote

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

// Entity -> wejscie dla bazy danych
@Entity(tableName = "noteTable")
data class Note(
    val title: String?,
    val content: String?,
    val color: String?,
    val timestamp: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
