package com.example.notesappmvvmroomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.notesappmvvmroomdatabase.model.Notes
import com.example.notesappmvvmroomdatabase.dao.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val allNOtes: LiveData<List<Notes>> = notesDao.retrieveAllNotes()

    suspend fun insert(note: Notes) {
        notesDao.insert(note)
    }

    suspend fun update(note: Notes) {
        notesDao.update(note)
    }

    suspend fun delete(note: Notes) {
        notesDao.delete(note)
    }
}