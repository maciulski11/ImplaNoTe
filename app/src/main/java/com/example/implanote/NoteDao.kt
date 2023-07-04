package com.example.implanote

import androidx.lifecycle.LiveData
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

    // Query -> load note by id
    @Query("SELECT * FROM noteTable WHERE id = :noteId")
    suspend fun fetchNoteById(noteId: Int): Note?

    // Query -> load all notes
    @Query("SELECT * FROM noteTable")
    fun fetchNotes(): LiveData<List<Note>>
}