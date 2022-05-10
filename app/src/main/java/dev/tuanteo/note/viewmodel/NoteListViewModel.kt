package dev.tuanteo.note.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tuanteo.note.data.NoteRepository
import dev.tuanteo.note.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository : NoteRepository
) : ViewModel() {

    /*TuanTeo: Tao cau truc dong goi cho LiveData */
    val listNotes: LiveData<List<Note>> = noteRepository.getAllNote().asLiveData()

    private var swipedNote: Note? = null

    fun saveSwipedNoteID(note: Note) {
        swipedNote = note
    }

    fun deleteSwipedNote() {
        viewModelScope.launch {
            noteRepository.delete(swipedNote!!)
        }
    }


}