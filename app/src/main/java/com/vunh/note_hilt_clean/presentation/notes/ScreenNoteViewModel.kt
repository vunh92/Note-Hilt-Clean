package com.vunh.note_hilt_clean.presentation.notes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.domain.use_cases.NoteUseCases
import com.vunh.note_hilt_clean.presentation.util.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ScreenNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel(), CoroutineScope {

    private var job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    init {
        getNotes()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    val notesResult = MutableLiveData<List<Note>>()
    val deleteResult = MutableLiveData<Boolean>()
    private var recentlyDeletedNote: Note? = null

    fun getNotes() {
        noteUseCases.getNotes().onEach {
            notesResult.value = it
        }.launchIn(viewModelScope)
    }

    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(notesEvent.note)
                    recentlyDeletedNote = notesEvent.note
                    deleteResult.value = true
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    deleteResult.value = false
                }
            }
        }
    }
}