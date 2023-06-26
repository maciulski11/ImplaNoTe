package com.example.implanote

import androidx.room.*

// na jakie operacje zezwalamy w bazie danych
@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(vararg note: Note)

    @Update
    suspend fun updateNote(vararg note: Note)

    @Delete
    suspend fun deleteNote(vararg note: Note)

    // Query -> zapytanie
    @Query("SELECT * FROM ${Note.NOTE_TABLE}")
    fun fetchNotes(): List<Note>
}