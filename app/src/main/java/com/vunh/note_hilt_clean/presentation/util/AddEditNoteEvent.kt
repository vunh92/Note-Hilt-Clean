package com.vunh.note_hilt_clean.presentation.util

sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}
