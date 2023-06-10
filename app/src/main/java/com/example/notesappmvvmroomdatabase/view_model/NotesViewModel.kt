package com.example.notesappmvvmroomdatabase.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesappmvvmroomdatabase.model.Notes
import com.example.notesappmvvmroomdatabase.model.NotesApp
import com.example.notesappmvvmroomdatabase.dao.NotesDao
import com.example.notesappmvvmroomdatabase.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val retrieveNotes: LiveData<List<Notes>>
    private val repository: NotesRepository
    private val notesDao: NotesDao

    init {
        // yahan change kya wa hai main ne bus!!
        notesDao = (application as NotesApp).db.notesDao()
        repository = NotesRepository(notesDao)
        retrieveNotes = repository.retrieveNotes
    }

    fun addNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun updateNote(note:Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun deleteNote(note: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
}