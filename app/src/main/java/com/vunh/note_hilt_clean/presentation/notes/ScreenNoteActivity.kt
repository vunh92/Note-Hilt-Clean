package com.vunh.note_hilt_clean.presentation.notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vunh.note_hilt_clean.R
import com.vunh.note_hilt_clean.databinding.ActivityScreenNoteBinding
import com.vunh.note_hilt_clean.presentation.adapter.NoteAdapter
import com.vunh.note_hilt_clean.presentation.add_note.AddNoteActivity
import com.vunh.note_hilt_clean.presentation.update_note.UpdateNoteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScreenNoteBinding
    private val viewModel: ScreenNoteViewModel by viewModels()
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        initializeView()
        initializeViewModel()
    }

    private fun initializeView(){
        binding.insertBtn.setOnClickListener {
            val intent = intentAddNote(this)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
        binding.recyclerRefreshBtn.setOnClickListener{
            viewModel.getNotes()
        }
        noteAdapter.deleteItemListener {
            viewModel.onEvent(NotesEvent.DeleteNote(it))
        }
        noteAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putInt("note_id", it)
            val intent = intentUpdateNote(this)
            intent.putExtra("bundleUpdateNote", bundle)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    private fun initializeViewModel(){
        viewModel.notesResult.observe(this, Observer {
            it?.apply {
                noteAdapter.noteList = it
            }
        })
        viewModel.deleteResult.observe(this, Observer {
            if (it) {
                Snackbar.make(binding.root,"Deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        viewModel.onEvent(NotesEvent.RestoreNote)
                    }
                    .show()
            }
        })
    }

    private fun setupAdapter() {
        noteAdapter = NoteAdapter()
        binding.noteRv.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(this@ScreenNoteActivity)
        }
    }

    companion object {
        fun intentAddNote(context: Context): Intent {
            return Intent(context, AddNoteActivity::class.java)
        }
        fun intentUpdateNote(context: Context): Intent {
            return Intent(context, UpdateNoteActivity::class.java)
        }
    }
}