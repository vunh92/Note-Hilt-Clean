package com.vunh.note_hilt_clean.presentation.add_note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunh.note_hilt_clean.domain.model.Note
import com.vunh.note_hilt_clean.domain.use_cases.NoteUseCases
import com.vunh.note_hilt_clean.presentation.util.AddEditNoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private var _noteTitle = ""
    private var _noteContent = ""
    val addResult = MutableLiveData<Boolean>()

    fun onEvent(event: AddEditNoteEvent) {
        viewModelScope.launch {
            when (event) {
                is AddEditNoteEvent.EnteredTitle -> {
                    _noteTitle = event.value
                }
                is AddEditNoteEvent.EnteredContent -> {
                    _noteContent = event.value
                }
                is AddEditNoteEvent.SaveNote -> {
                    viewModelScope.launch {
                        noteUseCases.addNote(
                            Note(
                                title = _noteTitle,
                                content = _noteContent,
                                timestamp = System.currentTimeMillis(),
                            )
                        )
                        addResult.value = true
                    }
                }
            }
        }
    }
}