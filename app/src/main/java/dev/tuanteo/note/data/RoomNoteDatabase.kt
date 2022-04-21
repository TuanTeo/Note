package dev.tuanteo.note.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.tuanteo.note.model.Note
import dev.tuanteo.note.utilities.Constants

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class RoomNoteDatabase : RoomDatabase() {
    abstract fun noteDatabaseDao() : NoteDatabaseDAO

    companion object {
        /*Dữ liệu của biến này không lưu vào bộ nhớ đệm, đảm bảo dữ liệu được đọc/ghi trên bộ nhớ chính
        * Giúp dữ liệu up to date liên tục, đồng bộ*/
        @Volatile
        private var INSTANCE : RoomNoteDatabase? = null

        fun getInstance(context : Context) : RoomNoteDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomNoteDatabase::class.java,
                        Constants.NOTE_DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance

            }
        }
    }
}