package com.example.implanote

import android.app.AlertDialog
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

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
                putInt("noteId", note.id)
                putInt("color", note.color.toString().toInt())
            }

            v.findNavController().navigate(R.id.action_noteFragment_to_noteEditFragment, bundle)
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

            titleNote.text = note.title
            contentNote.text = note.content
            dateNote.text = note.timestamp

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