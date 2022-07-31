package com.vunh.note_hilt_clean.di

import android.app.Application
import androidx.room.Room
import com.vunh.note_hilt_clean.data.data_source.NoteDatabase
import com.vunh.note_hilt_clean.data.repositories.NoteRepositoryImpl
import com.vunh.note_hilt_clean.domain.repositories.NoteRepository
import com.vunh.note_hilt_clean.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : NoteDatabase = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        NoteDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase) : NoteRepository =
        NoteRepositoryImpl(noteDatabase.noteDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases =
        NoteUseCases(
            getNotes = GetNotes(noteRepository),
            getNote = GetNote(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            updateNote = UpdateNote(noteRepository),
            addNote = AddNote(noteRepository)
        )
}