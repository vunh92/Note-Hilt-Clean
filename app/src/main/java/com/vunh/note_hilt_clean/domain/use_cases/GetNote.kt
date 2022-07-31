package com.vunh.note_hilt_clean.domain.use_cases

import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.domain.repositories.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNoteById(id)
    }
}