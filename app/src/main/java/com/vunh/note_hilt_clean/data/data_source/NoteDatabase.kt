package com.vunh.note_hilt_clean.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vunh.note_hilt_clean.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "note_database"
    }
}
