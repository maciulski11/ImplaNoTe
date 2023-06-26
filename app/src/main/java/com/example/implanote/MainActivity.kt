package com.example.implanote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

//    val dao = NoteDatabase.fetchInstance(applicationContext)!!.noteDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val note = listOf(
//        Note("fdvf", "efvdv"),
//        Note("Dfvdf", "fdvd")
//        )
//
//        lifecycleScope.launch {
//            note.forEach{ dao.fetchNotes() }
//        }
    }
}