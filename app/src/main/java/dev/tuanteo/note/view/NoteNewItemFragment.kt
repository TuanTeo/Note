package dev.tuanteo.note.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.tuanteo.note.R
import dev.tuanteo.note.databinding.NoteNewItemBinding
import dev.tuanteo.note.viewmodel.NoteNewItemViewModel

@AndroidEntryPoint
class NoteNewItemFragment : Fragment() {

    private val viewModel : NoteNewItemViewModel by viewModels()
    private val args: NoteNewItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding : NoteNewItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_new_item, container, false)

        if (args.noteId.toString() != "") {
            viewModel.getNote(args.noteId.toLong())
        }

        viewModel.note?.observe(viewLifecycleOwner) {
            binding.note = it
        }

        binding.submitButton.setOnClickListener {

            val note = binding.note
            var noteID: Long? = null
            if (note != null) {
                noteID = note.id
            }


            /*TuanTeo: Luu thong tin note moi */
            viewModel.saveNote(
                noteID,
                binding.noteTitle.text.toString().trim(),
                binding.noteContent.text.toString().trim()
            )

            findNavController().navigate(
                if (args.noteId.toString() != "") {
                    NoteNewItemFragmentDirections.actionNoteNewItemFragmentToNoteDetailItemFragment(
                        noteID.toString())
                } else {
                    NoteNewItemFragmentDirections.actionNoteNewItemFragmentToNoteListFragment()
                }
            )
        }

        return binding.root
    }
}