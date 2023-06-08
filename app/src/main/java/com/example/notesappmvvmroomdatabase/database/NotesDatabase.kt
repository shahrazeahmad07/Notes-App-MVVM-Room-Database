package com.example.notesappmvvmroomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesappmvvmroomdatabase.dao.NotesDao
import com.example.notesappmvvmroomdatabase.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE : NotesDatabase? = null

        fun getDatabase(context: Context) : NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}