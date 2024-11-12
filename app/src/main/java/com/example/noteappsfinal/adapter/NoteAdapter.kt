package com.example.noteappsfinal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappsfinal.R
import com.example.noteappsfinal.databinding.ItemNoteBinding
import com.example.noteappsfinal.model.NoteModel

class NoteAdapter(private val items: List<NoteModel>, private val onItemClick: (noteData: NoteModel) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class NoteViewHolder(var itemNoteBinding: ItemNoteBinding) : RecyclerView.ViewHolder(itemNoteBinding.root) {
        fun bind(noteData: NoteModel?) {
            itemNoteBinding.noteData = noteData
            itemNoteBinding.executePendingBindings()
        }
    }
}
