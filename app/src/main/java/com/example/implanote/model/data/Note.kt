package com.example.implanote.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

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
