package dev.tuanteo.note.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.tuanteo.note.data.NoteDatabaseDAO
import dev.tuanteo.note.data.RoomNoteDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NoteDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomNoteDatabase(@ApplicationContext context: Context): RoomNoteDatabase {
        return RoomNoteDatabase.getInstance(context)
    }

    @Provides
    fun provideNoteDatabaseDao(appDatabase: RoomNoteDatabase): NoteDatabaseDAO {
        return appDatabase.noteDatabaseDao()
    }
}