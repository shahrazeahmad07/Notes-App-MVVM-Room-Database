package com.example.notesappmvvmroomdatabase.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesappmvvmroomdatabase.model.Notes
import com.example.notesappmvvmroomdatabase.model.NotesApp
import com.example.notesappmvvmroomdatabase.dao.NotesDao
import com.example.notesappmvvmroomdatabase.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Notes>>
    val repository: NotesRepository
    val notesDao: NotesDao

    init {
        notesDao = (application as NotesApp).db.notesDao()
        repository = NotesRepository(notesDao)
        allNotes = repository.allNOtes
    }

    fun addNote(note: Notes) = viewModelScope.launch {
        repository.insert(note)
    }

    fun updateNote(note:Notes) = viewModelScope.launch {
        repository.update(note)
    }

    fun deleteNote(note: Notes) = viewModelScope.launch {
        repository.delete(note)
    }
}