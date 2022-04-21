package dev.tuanteo.note.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.tuanteo.note.R
import dev.tuanteo.note.databinding.NoteNewItemBinding
import dev.tuanteo.note.viewmodel.NoteNewItemViewModel

@AndroidEntryPoint
class NoteNewItemFragment : Fragment() {

    private val viewModel : NoteNewItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding : NoteNewItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_new_item, container, false)

        binding.submitButton.setOnClickListener {
            /*TuanTeo: Luu thong tin note moi */
            viewModel.saveNote(
                null,
                binding.noteTitle.text.toString(),
                binding.noteContent.text.toString()
            )

            findNavController().navigate(
                // TODO: Check được mở từ fragment nào để hiển thị lại phù hợp
                NoteNewItemFragmentDirections.actionNoteNewItemFragmentToNoteListFragment())
//                NoteNewItemFragmentDirections.actionNoteNewItemFragmentToNoteDetailItemFragment())
        }

        return binding.root
    }
}