package com.example.notesappmvvmroomdatabase.model

import android.app.Application
import com.example.notesappmvvmroomdatabase.database.NotesDatabase

class NotesApp: Application() {
    val db by lazy {
        NotesDatabase.getDatabase(this)
    }
}