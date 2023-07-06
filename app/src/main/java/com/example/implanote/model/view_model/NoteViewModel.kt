package com.example.implanote.model.view_model

import android.app.Application
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.implanote.NoteDatabase
import com.example.implanote.services.repository.NoteRepository
import com.example.implanote.R
import com.example.implanote.model.data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {

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
            withContext(Dispatchers.Main) {
                val title = view.findViewById<EditText>(R.id.titleEditText)
                val content = view.findViewById<EditText>(R.id.contentEditText)
                val background = view.findViewById<ConstraintLayout>(R.id.editNoteBackground)


                title.setText(note?.title)
                content.setText(note?.content)
                background.setBackgroundColor(note?.color.toString().toInt())

            }
        }
    }
}