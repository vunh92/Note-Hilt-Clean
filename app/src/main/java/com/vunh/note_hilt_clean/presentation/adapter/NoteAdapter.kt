package com.vunh.note_hilt_clean.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vunh.note_hilt_clean.R
import com.vunh.note_hilt_clean.domain.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var noteList =  listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.bind(note)
        holder.delete.setOnClickListener {
            onDeleteItemListener?.let { it(note) }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(note.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title_tv)
        val content: TextView = itemView.findViewById(R.id.content_tv)
        val delete: ImageButton = itemView.findViewById(R.id.delete_bt)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.note_item, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: Note) {
            title.text = item.title
            content.text = item.content
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null
    private var onDeleteItemListener: ((Note) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    fun deleteItemListener(listener: (Note) -> Unit) {
        onDeleteItemListener = listener
    }
}