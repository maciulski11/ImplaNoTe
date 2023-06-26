package com.example.implanote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity -> wejscie dla bazy danych
@Entity(tableName = "note_table")
data class Note(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "note") val note: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    companion object {
        const val NOTE_TABLE = "note_table"
    }
}
