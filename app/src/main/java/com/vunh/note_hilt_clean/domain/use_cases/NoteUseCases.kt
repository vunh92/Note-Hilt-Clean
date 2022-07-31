package com.vunh.note_hilt_clean.domain.use_cases


data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val updateNote: UpdateNote,
    val getNote: GetNote
)
