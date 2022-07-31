package com.vunh.note_hilt_clean.domain.repositories

import androidx.lifecycle.LiveData
import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.presentation.util.UseCaseResult
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id:Int):Note?

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}