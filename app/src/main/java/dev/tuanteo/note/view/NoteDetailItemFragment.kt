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
import dev.tuanteo.note.databinding.NoteDetailItemBinding
import dev.tuanteo.note.viewmodel.NoteDetailViewModel
import javax.inject.Inject

@AndroidEntryPoint
class NoteDetailItemFragment : Fragment() {

    private val viewModel : NoteDetailViewModel by viewModels()
    private val args:NoteDetailItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding : NoteDetailItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_detail_item, container, false)

        viewModel.getNote(args.noteId.toLong())

        viewModel.note?.observe(viewLifecycleOwner) {
            binding.note = it
        }

        binding.closeButton.setOnClickListener {
            findNavController().navigate(
                NoteDetailItemFragmentDirections.actionNoteDetailItemFragmentToNoteNewItemFragment(
                    binding.note!!.id.toString()
                ))
        }

        return binding.root
    }
}