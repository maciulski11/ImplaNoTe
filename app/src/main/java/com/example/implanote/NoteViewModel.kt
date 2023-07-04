package com.example.implanote

import android.app.Application
import android.view.View
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val note: MutableLiveData<Note?> = MutableLiveData()
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.fetchInstance(application)?.noteDao()
        repository = NoteRepository(dao!!)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun fetchNoteById(noteId: Int, view: View) = viewModelScope.launch(Dispatchers.IO) {
        repository.fetchNoteById(noteId).let { note ->
            val title = view.findViewById<EditText>(R.id.titleEditText)
            val content = view.findViewById<EditText>(R.id.contentEditText)

            title.setText(note?.title)
            content.setText(note?.content)
        }

    }
}