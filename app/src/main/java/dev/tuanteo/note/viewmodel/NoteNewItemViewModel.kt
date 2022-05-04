package dev.tuanteo.note.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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

    var note: LiveData<Note>? = null

    fun saveNote(idNote: Long?, title: String, content: String) {
        if (title == "" || content == "") {
            // Do nothing
            return
        }

        if (idNote == null) {
            /*TuanTeo: Them Note moi */
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Note(title = title, content = content, date = DateUtils.getCurrentDateAsString())
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            viewModelScope.launch {
                noteRepository.insertNote(note)
            }
        } else {
                val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Note(id = idNote, title, content, date = DateUtils.getCurrentDateAsString())
                } else {
                    TODO("VERSION.SDK_INT < O")
                }

            viewModelScope.launch {
                noteRepository.update(note)
            }
        }

    }


    fun getNote(id: Long) {
        note = noteRepository.get(id).asLiveData()
    }

}