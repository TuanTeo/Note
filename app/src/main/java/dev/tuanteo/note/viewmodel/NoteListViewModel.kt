package dev.tuanteo.note.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tuanteo.note.data.NoteRepository
import dev.tuanteo.note.model.Note
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository : NoteRepository
) : ViewModel() {

    fun getAllNote(context: Context) {
        viewModelScope.launch {
            displayAllNote(context)
        }
    }

    private suspend fun displayAllNote(context : Context) {
        val notes : List<Note> = noteRepository.getAllNote()

        for (note in notes) {
            Toast.makeText(context, note.content, Toast.LENGTH_LONG).show()
        }
    }


}