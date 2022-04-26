package dev.tuanteo.note.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.tuanteo.note.databinding.NoteItemBinding
import dev.tuanteo.note.model.Note

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        if (note != null) {
            holder.bind(note, position != 0)
        }
    }

    class NoteViewHolder (
        private val binding: NoteItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.noteContainer.setOnClickListener {
            }
        }

        fun bind(item: Note, hasDivide: Boolean) {
            if (hasDivide) {
                binding.divide.visibility = View.VISIBLE
            }

            binding.apply {
                note = item
                executePendingBindings()
            }
        }

    }
}

private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}


