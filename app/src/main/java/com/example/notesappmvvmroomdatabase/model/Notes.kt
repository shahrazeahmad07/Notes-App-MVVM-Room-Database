package com.example.notesappmvvmroomdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notes-table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteTitle: String = "",
    val noteContent: String = "",
    val noteTimeStamp: String = ""
)