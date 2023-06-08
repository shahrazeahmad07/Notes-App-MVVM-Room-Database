package com.example.notesappmvvmroomdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notes-table")
class Notes (
    @ColumnInfo(name = "title")
    var noteTitle: String = "",
    @ColumnInfo(name = "Description")
    var noteDescription: String = "",
    @ColumnInfo(name = "timeStamp")
    var noteTimeStamp: String = ""
    ) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}