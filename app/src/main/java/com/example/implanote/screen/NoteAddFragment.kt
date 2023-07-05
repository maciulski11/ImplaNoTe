package com.example.implanote.screen

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.implanote.BaseFragment
import com.example.implanote.Note
import com.example.implanote.NoteViewModel
import com.example.implanote.R
import kotlinx.android.synthetic.main.fragment_note_add.*

class NoteAddFragment : BaseFragment() {
    override val layout: Int = R.layout.fragment_note_add

    var selectedColor: Int = 0

    private val viewModel: NoteViewModel by viewModels()

    override fun subscribeUi() {

        addButton.setOnClickListener {

            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            val addNote = Note(title, content, selectedColor.toString())
            viewModel.insert(addNote)

            findNavController().navigate(R.id.action_addNoteFragment_to_noteFragment)
        }
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
                addNoteBackground.setBackgroundColor(color)
            }
            linearLayout.addView(button)
        }

    }

    override fun unsubscribeUi() {

    }
}