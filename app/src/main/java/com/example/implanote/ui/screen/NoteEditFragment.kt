package com.example.implanote.ui.screen

import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.implanote.*
import com.example.implanote.base.BaseFragment
import com.example.implanote.model.data.Note
import com.example.implanote.model.view_model.NoteViewModel
import com.example.implanote.services.repository.NoteRepository
import com.example.implanote.services.utils.AnimationUtils
import com.example.implanote.services.utils.ColorUtils
import com.example.implanote.services.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_note_edit.*
import kotlinx.android.synthetic.main.fragment_note_edit.contentEditText
import kotlinx.android.synthetic.main.fragment_note_edit.linearLayout
import kotlinx.android.synthetic.main.fragment_note_edit.titleEditText
import java.text.SimpleDateFormat
import java.util.*

class NoteEditFragment : BaseFragment() {
    override val layout: Int = R.layout.fragment_note_edit

    private var selectedColor: Int = 0

    private val viewModel: NoteViewModel by viewModels()

    override fun subscribeUi() {

        val noteId = requireArguments().getInt(NoteRepository.NOTE_ID)
        selectedColor = requireArguments().getInt(NoteRepository.NOTE_COLOR)

        updateButton.setOnClickListener {

            val currentTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat(NoteRepository.DATE_FORMAT, Locale.getDefault())
            val formattedTime = dateFormat.format(currentTime)

            val updateNote =
                Note(
                    titleEditText.text.toString(),
                    contentEditText.text.toString(),
                    selectedColor.toString(),
                    formattedTime,
                    noteId
                )

            viewModel.update(updateNote)

            findNavController().navigate(
                R.id.action_noteEditFragment_to_noteFragment,
                null,
                AnimationUtils.downNavAnim
            )

            ToastUtils.customToast(
                requireContext(),
                "Successful update!",
                R.drawable.ic_baseline_check_circle_outline_24,
                R.color.green
            )
        }

        viewModel.fetchNoteById(noteId, requireView())

        changeBackgroundColor()
    }

    private fun changeBackgroundColor() {

        val colorResIds = ColorUtils.colorList

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Handler().postDelayed({
            inflater.inflate(R.menu.return_button, menu)
        }, 300)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.returnNoteList -> {
                findNavController().navigate(
                    R.id.action_noteEditFragment_to_noteFragment,
                    null,
                    AnimationUtils.rightNavAnim)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun unsubscribeUi() {

    }
}