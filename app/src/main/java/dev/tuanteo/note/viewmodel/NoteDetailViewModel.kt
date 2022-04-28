package dev.tuanteo.note.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tuanteo.note.data.NoteRepository
import dev.tuanteo.note.model.Note
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteRepository : NoteRepository
) : ViewModel() {

    private var _note = MutableLiveData<Note>()
    var note: LiveData<Note>? = null

    fun getNote(id: Long) {
        note = noteRepository.get(id).asLiveData()
    }
}