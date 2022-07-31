package com.vunh.note_hilt_clean.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vunh.note_hilt_clean.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("select * from note")
    fun getNotes(): Flow<List<Note>>

    @Query("select * from note where id=:id")
    suspend fun getNoteById(id:Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}