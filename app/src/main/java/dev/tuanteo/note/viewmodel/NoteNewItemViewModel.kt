package dev.tuanteo.note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tuanteo.note.data.NoteRepository
import dev.tuanteo.note.model.Note
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteNewItemViewModel @Inject constructor(
    private val noteRepository : NoteRepository
) : ViewModel() {

    fun saveNote(idNote: String?, title: String, content: String) {
        if (idNote == null) {
            /*TuanTeo: Them Note moi */
            val note = Note(title = title, content = content)

            if (note.content == "" && note.title == "") {
                // Do nothing
                return
            }

            viewModelScope.launch {
                noteRepository.insertNote(note)
            }
        } else {
            /*TuanTeo: Cap nhat Note cu */
        }

    }

}