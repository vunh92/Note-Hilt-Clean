package com.vunh.note_hilt_clean.presentation.add_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.vunh.note_hilt_clean.R
import com.vunh.note_hilt_clean.databinding.ActivityAddNoteBinding
import com.vunh.note_hilt_clean.presentation.util.AddEditNoteEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
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

    private fun initializeView(){
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        binding.acceptBtn.setOnClickListener {
            viewModel.onEvent(AddEditNoteEvent.EnteredTitle(binding.titleEdt.text.toString().trim()))
            viewModel.onEvent(AddEditNoteEvent.EnteredContent(binding.contentEdt.text.toString().trim()))
            viewModel.onEvent(AddEditNoteEvent.SaveNote)
        }
    }

    private fun initializeViewModel(){
        viewModel.addResult.observe(this, Observer {
            if(it) {
                onBackPressed()
            }
        })
    }
}