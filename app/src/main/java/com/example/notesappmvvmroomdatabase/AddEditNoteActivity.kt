package com.example.notesappmvvmroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notesappmvvmroomdatabase.databinding.ActivityAddEditNoteBinding
import com.example.notesappmvvmroomdatabase.model.Notes
import com.example.notesappmvvmroomdatabase.view_model.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddEditNoteActivity : AppCompatActivity() {
    private var binding: ActivityAddEditNoteBinding? = null
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //! View Model
        notesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModel::class.java]

        //! save button
        binding?.btnSaveNote?.setOnClickListener {
            val noteTitle = binding?.etNoteTitle?.text?.toString()
            val noteContent = binding?.etNoteContent?.text?.toString()
            if (noteTitle!!.isNotEmpty() && noteContent!!.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.US)
                val currentDateAndTime = sdf.format(Date())
                notesViewModel.addNote (
                    Notes(
                        noteTitle = noteTitle,
                        noteContent = noteContent,
                        noteTimeStamp = currentDateAndTime
                    )
                )
                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (binding != null) {
            binding = null
        }
    }
}