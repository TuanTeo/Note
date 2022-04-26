package dev.tuanteo.note.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.tuanteo.note.R
import dev.tuanteo.note.adapter.NoteListAdapter
import dev.tuanteo.note.databinding.NoteListFragmentBinding
import dev.tuanteo.note.viewmodel.NoteListViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val adapter = NoteListAdapter()
    private val viewModel : NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding : NoteListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_list_fragment, container, false)

        binding.addNoteButton.setOnClickListener {
            findNavController().navigate(NoteListFragmentDirections.actionNoteListFragmentToNoteNewItemFragment())
        }

        binding.listNotesRecyclerview.adapter = adapter

        /*TuanTeo: Set data cho recycler view */
        setDataOnView()

        return binding.root
    }

    private fun setDataOnView() {
        lifecycleScope.launch {
            adapter.submitList(viewModel.displayAllNote())
        }
    }
}