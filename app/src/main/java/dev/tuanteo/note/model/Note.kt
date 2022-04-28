package dev.tuanteo.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.tuanteo.note.utilities.Constants

@Entity(tableName = Constants.NOTE_DATABASE_NAME)
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Long = 0,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "date") val date: String)