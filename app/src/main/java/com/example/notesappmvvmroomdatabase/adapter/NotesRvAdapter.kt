package com.example.notesappmvvmroomdatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvvmroomdatabase.R
import com.example.notesappmvvmroomdatabase.model.Notes

class NotesRvAdapter(
    private val noteClickListener: (note: Notes) -> Unit,
    private val deleteNoteListener: (note: Notes) -> Unit
) : RecyclerView.Adapter<NotesRvAdapter.ViewHolder>() {

    private val allNotesList: ArrayList<Notes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.single_note_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return allNotesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvRvTitle.text = allNotesList[position].noteTitle
        holder.tvRvTimeStamp.text = "Last Updated: ${allNotesList[position].noteTimeStamp}"
        holder.itemView.setOnClickListener {
            noteClickListener.invoke(allNotesList[position])
        }
        holder.ivRvDeleteButton.setOnClickListener {
            deleteNoteListener.invoke(allNotesList[position])
        }
    }

    fun updateList(newList: List<Notes>) {
        allNotesList.clear()
        allNotesList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRvTitle: TextView
        val tvRvTimeStamp: TextView
        val ivRvDeleteButton: ImageView
        init {
            tvRvTitle = itemView.findViewById(R.id.tvRvTitle)
            tvRvTimeStamp = itemView.findViewById(R.id.tvRvTimeStamp)
            ivRvDeleteButton = itemView.findViewById(R.id.ivRvDeleteButton)
        }
    }
}