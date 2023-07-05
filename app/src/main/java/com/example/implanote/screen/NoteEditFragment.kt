package com.example.implanote.screen

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.implanote.*
import kotlinx.android.synthetic.main.fragment_note_edit.*
import kotlinx.android.synthetic.main.fragment_note_edit.contentEditText
import kotlinx.android.synthetic.main.fragment_note_edit.linearLayout
import kotlinx.android.synthetic.main.fragment_note_edit.titleEditText

class NoteEditFragment : BaseFragment() {
    override val layout: Int = R.layout.fragment_note_edit

    private var selectedColor: Int = 0

    private val viewModel: NoteViewModel by viewModels()

    override fun subscribeUi() {

        val noteId = requireArguments().getInt("noteId")

        updateButton.setOnClickListener {

            val updateNote =
                Note(
                    titleEditText.text.toString(),
                    contentEditText.text.toString(),
                    selectedColor.toString(),
                    noteId
                )

            viewModel.update(updateNote)

            findNavController().navigate(R.id.action_noteEditFragment_to_noteFragment)
        }

        viewModel.fetchNoteById(noteId, requireView())

        changeBackgroundColor()
    }

    private fun changeBackgroundColor() {

        val colorResIds = listOf(
            R.color.yellow,
            R.color.babyBlue,
            R.color.orange,
            R.color.mint,
            R.color.red,
            R.color.white
        )

        for (colorResId in colorResIds) {
            val button = ImageView(requireContext())
            button.layoutParams =
                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            button.setImageResource(R.drawable.ic_baseline_circle_24)

            val color = ContextCompat.getColor(requireContext(), colorResId)
            button.setColorFilter(color)
            button.setOnClickListener {
                selectedColor = color
                editNoteBackground.setBackgroundColor(color)
            }
            linearLayout.addView(button)
        }
    }

    override fun unsubscribeUi() {

    }
}