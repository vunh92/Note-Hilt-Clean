package com.vunh.note_hilt_clean.domain.use_cases

import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.domain.repositories.NoteRepository

class DeleteNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}