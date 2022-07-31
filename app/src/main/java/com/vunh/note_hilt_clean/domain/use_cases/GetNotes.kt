package com.vunh.note_hilt_clean.domain.use_cases

import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }
}