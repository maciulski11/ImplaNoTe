package com.example.implanote

import androidx.lifecycle.LiveData

class NoteRepository(private var noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.fetchNotes()

    suspend fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun fetchNoteById(noteId: Int): Note? {
        return noteDao.fetchNoteById(noteId)
    }
}