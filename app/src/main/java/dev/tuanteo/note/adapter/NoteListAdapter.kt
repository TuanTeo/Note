package dev.tuanteo.note.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.tuanteo.note.databinding.NoteItemBinding
import dev.tuanteo.note.model.Note
import dev.tuanteo.note.view.NoteListFragmentDirections

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
            /*TuanTeo: Mở trang giao diện xem thông tin 1 note */
            binding.noteContainer.setOnClickListener {
                it.findNavController().navigate(
                    NoteListFragmentDirections.actionNoteListFragmentToNoteDetailItemFragment(
                        binding.note!!.id.toString()
                    ))
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


