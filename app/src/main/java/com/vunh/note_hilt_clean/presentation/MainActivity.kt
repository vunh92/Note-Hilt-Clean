package com.vunh.note_hilt_clean.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vunh.note_hilt_clean.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}