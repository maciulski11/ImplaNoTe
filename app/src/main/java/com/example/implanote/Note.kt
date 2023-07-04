package com.example.implanote

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity -> wejscie dla bazy danych
@Entity(tableName = "noteTable")
data class Note(
    val title: String?,
    val content: String?,
    val color: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
