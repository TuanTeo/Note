package dev.tuanteo.note.data

import dev.tuanteo.note.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDao : NoteDatabaseDAO
) {

    fun getAllNote() = noteDatabaseDao.getAllNotes()

    suspend fun insertNote(note: Note) = noteDatabaseDao.insert(note)

    fun get(id: Long) = noteDatabaseDao.get(id)

    suspend fun update(note: Note) = noteDatabaseDao.update(note)

    suspend fun delete(note: Note) = noteDatabaseDao.delete(note)
}