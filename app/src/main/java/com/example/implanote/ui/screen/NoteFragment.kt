package com.example.implanote.ui.screen

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.implanote.*
import com.example.implanote.base.BaseFragment
import com.example.implanote.model.data.Note
import com.example.implanote.model.view_model.NoteViewModel
import com.example.implanote.services.repository.NoteRepository
import com.example.implanote.ui.adapter.NoteAdapter
import com.example.implanote.services.utils.AnimationUtils
import kotlinx.android.synthetic.main.fragment_note.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteFragment : BaseFragment() {
    override val layout: Int = R.layout.fragment_note

    private lateinit var noteList: ArrayList<Note>
    private lateinit var adapter: NoteAdapter
    private val viewModel: NoteViewModel by viewModels()

    override fun subscribeUi() {

        recyclerViewNote.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerViewNote.setHasFixedSize(true)

        noteList = arrayListOf()

        adapter = NoteAdapter(
            noteList,
            requireView(),
        )
        { note ->
            viewModel.delete(note)
        }

        recyclerViewNote.adapter = adapter

        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                noteList.clear()
                noteList.addAll(list)
                noteList.sortByDescending {
                    SimpleDateFormat(
                        NoteRepository.DATE_FORMAT,
                        Locale.getDefault()
                    ).parse(it.timestamp.toString())
                }
                adapter.notifyDataSetChanged()
            }
        })

        addNoteButton.setOnClickListener {

            findNavController().navigate(
                R.id.action_noteFragment_to_addNoteFragment,
                null,
                AnimationUtils.topNavAnim
            )
        }
    }

    override fun unsubscribeUi() {

    }
}