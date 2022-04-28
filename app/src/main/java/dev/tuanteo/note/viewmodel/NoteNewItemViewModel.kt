package dev.tuanteo.note.viewmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tuanteo.note.data.NoteRepository
import dev.tuanteo.note.model.Note
import dev.tuanteo.note.utilities.DateUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteNewItemViewModel @Inject constructor(
    private val noteRepository : NoteRepository
) : ViewModel() {

    fun saveNote(idNote: String?, title: String, content: String) {
        if (idNote == null) {
            /*TuanTeo: Them Note moi */
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Note(title = title, content = content, date = DateUtils.getCurrentDateAsString())
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            if (note.content == "" && note.title == "") {
                // Do nothing
                return
            }

            viewModelScope.launch {
                noteRepository.insertNote(note)
            }
        } else {
            /*TODO TuanTeo: Cap nhat Note cu */
        }

    }

}