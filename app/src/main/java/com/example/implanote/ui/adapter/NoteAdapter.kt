package com.example.implanote.ui.adapter

import android.app.AlertDialog
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.implanote.services.repository.NoteRepository
import com.example.implanote.R
import com.example.implanote.model.data.Note
import com.example.implanote.services.utils.AnimationUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteAdapter(
    private val noteList: ArrayList<Note>,
    private val v: View,
    val onDeleteNote: (Note) -> Unit
) :
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = noteList[position]

        holder.itemNote.setOnClickListener {
            val bundle = Bundle().apply {
                putInt(NoteRepository.NOTE_ID, note.id)
                putInt(NoteRepository.NOTE_COLOR, note.color.toString().toInt())
            }

            v.findNavController().navigate(
                R.id.action_noteFragment_to_noteEditFragment,
                bundle,
                AnimationUtils.leftNavAnim
            )
        }

        holder.bind(note)
        holder.itemNote.setOnLongClickListener(holder)
    }

    override fun getItemCount(): Int = noteList.size

    inner class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnLongClickListener {

        val itemNote: RelativeLayout = view.findViewById(R.id.itemNote)
        private val titleNote: TextView = view.findViewById(R.id.titleTextView)
        private val contentNote: TextView = view.findViewById(R.id.contentTextView)
        private val dateNote: TextView = view.findViewById(R.id.dateTextView)


        fun bind(note: Note) {

            val originalFormat = SimpleDateFormat(NoteRepository.DATE_FORMAT, Locale.getDefault())
            val newFormat = SimpleDateFormat("dd-MM-yy  HH:mm", Locale.getDefault())

            titleNote.text = note.title
            contentNote.text = note.content
            dateNote.text = originalFormat.parse(note.timestamp.toString())
                ?.run { newFormat.format(this) }

            val backgroundDrawable = itemNote.background as? GradientDrawable
            backgroundDrawable?.setColor(note.color.toString().toInt())
        }

        override fun onLongClick(v: View?): Boolean {
            val note = noteList[adapterPosition]

            val message = "Do you want to delete your note: <strong>${note.title}</strong>?"

            val alertDialog = AlertDialog.Builder(view.context)
                .setTitle("Delete note!")
                .setMessage(Html.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setPositiveButton("OK") { dialog, which ->

                    onDeleteNote(note)
                }
                .setNegativeButton("Cancel") { dialog, which ->

                }
                .create()

            alertDialog.show()

            return true
        }
    }
}