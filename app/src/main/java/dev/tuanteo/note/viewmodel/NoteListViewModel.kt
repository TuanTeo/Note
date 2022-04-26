package dev.tuanteo.note.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tuanteo.note.data.NoteRepository
import dev.tuanteo.note.model.Note
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository : NoteRepository
) : ViewModel() {

    /*TuanTeo: Tao cau truc dong goi cho LiveData */
    private val _listNotes = MutableLiveData<List<Note>>()
    val listNotes: LiveData<List<Note>>
        get() = _listNotes


    suspend fun displayAllNote(): List<Note> {
        // TODO: Fragment theo doi bien nay de update adapter
        return noteRepository.getAllNote()
    }


}