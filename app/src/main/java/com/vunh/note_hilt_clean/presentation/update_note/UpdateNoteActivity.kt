package com.vunh.note_hilt_clean.presentation.update_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vunh.note_hilt_clean.R
import com.vunh.note_hilt_clean.databinding.ActivityScreenNoteBinding
import com.vunh.note_hilt_clean.databinding.ActivityUpdateNoteBinding
import com.vunh.note_hilt_clean.presentation.util.AddEditNoteEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    private val viewModel: UpdateNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeView()
        initializeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeView() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        val bundle: Bundle? = intent.getBundleExtra("bundleUpdateNote")
        bundle?.let {
            bundle.apply {
                val noteId = getInt("note_id", -1)
                if(noteId != -1) {
                    viewModel.getNote(noteId)
                }
            }
        }

        binding.acceptBtn.setOnClickListener {
            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(binding.titleEdt.text.toString().trim()))
            viewModel.onEvent(AddEditNoteEvent.EnteredContent(binding.contentEdt.text.toString().trim()))
            viewModel.onEvent(AddEditNoteEvent.SaveNote)
        }
    }

    private fun initializeViewModel() {
        viewModel.noteResult.observe(this, Observer {
            binding.titleEdt.setText(it.title)
            binding.contentEdt.setText(it.content)
        })
        viewModel.updateResult.observe(this, Observer {
            if(it) {
                onBackPressed()
            }
        })
    }
}