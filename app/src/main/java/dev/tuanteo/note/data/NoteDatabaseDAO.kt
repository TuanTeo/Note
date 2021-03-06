package dev.tuanteo.note.data

import androidx.room.*
import dev.tuanteo.note.model.Note
import dev.tuanteo.note.utilities.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDAO {
    @Insert
    suspend fun insert(note : Note)

    @Update
    suspend fun update(note : Note)

    @Query("Select * From ${Constants.NOTE_DATABASE_NAME}")
    fun getAllNotes() : Flow<List<Note>>

    /*TuanTeo: Dung dau : de anh xa doi so trong ham (VD: key) */
    @Query("Select * From ${Constants.NOTE_DATABASE_NAME} Where id = :key")
    fun get(key : Long) : Flow<Note>

    @Delete
    suspend fun delete(note: Note)
}