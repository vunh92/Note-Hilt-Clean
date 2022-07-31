package com.vunh.note_hilt_clean.domain.use_cases

import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.domain.repositories.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            return
        }
        if (note.content.isBlank()) {
            return
        }
        noteRepository.insertNote(note)
    }
}
