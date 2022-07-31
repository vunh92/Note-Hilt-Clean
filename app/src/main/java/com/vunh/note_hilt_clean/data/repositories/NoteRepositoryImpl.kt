package com.vunh.note_hilt_clean.data.repositories

import androidx.lifecycle.LiveData
import com.vunh.note_hilt_clean.data.data_source.NoteDao
import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.domain.repositories.NoteRepository
import com.vunh.note_hilt_clean.presentation.util.UseCaseResult
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

    override suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
}