package com.example.notesappmvvmroomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesappmvvmroomdatabase.model.Notes

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(note: Notes)

    @Update
    suspend fun update(note: Notes)

    @Delete
    suspend fun delete(note: Notes)

    @Query("Select * from `notes-table`")
    fun retrieveAllNotes(): LiveData<List<Notes>>
}