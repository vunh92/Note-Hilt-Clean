package com.vunh.note_hilt_clean.presentation.notes

import com.vunh.note_hilt_clean.domain.model.Note

sealed class NotesEvent {
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
}