package com.example.notesappmvvmroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappmvvmroomdatabase.adapter.NotesRvAdapter
import com.example.notesappmvvmroomdatabase.dao.NotesDao
import com.example.notesappmvvmroomdatabase.databinding.ActivityMainBinding
import com.example.notesappmvvmroomdatabase.model.Notes
import com.example.notesappmvvmroomdatabase.model.NotesApp
import com.example.notesappmvvmroomdatabase.view_model.NotesViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var notesRvAdapter: NotesRvAdapter
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //! setting action bar title
        supportActionBar?.title = "Your Notes"

        // Setting Recycler View:
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)

        //! setting View Model and creating adapter
        notesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModel::class.java]

        //! retrieving notes
        notesViewModel.retrieveNotes.observe(this) { list ->
            if (list.isEmpty()) {
                binding?.rvNotes?.visibility = View.GONE
                binding?.tvNoNotes?.visibility = View.VISIBLE
            } else {
                binding?.rvNotes?.visibility = View.VISIBLE
                binding?.tvNoNotes?.visibility = View.GONE
            }
            list.let { notesList ->
                notesRvAdapter = NotesRvAdapter(ArrayList(notesList),
                    { note ->
                        onNoteClick(note)
                    }, { note ->
                        showDeleteDialog(note)
                    })
                binding?.rvNotes?.adapter = notesRvAdapter
            }
        }

        //! floating Action button
        binding?.floatingActionButton?.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            intent.putExtra("noteType", "New")
            startActivity(intent)
        }
    }

    // on note click
    private fun onNoteClick(note: Notes) {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteContent", note.noteContent)
        intent.putExtra("noteTimeStamp", note.noteTimeStamp)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
    }


    //! on delete button clicked
    private fun showDeleteDialog(note: Notes) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete")
        val noteTitle = note.noteTitle
        builder.setMessage("Are you sure you want to delete: $noteTitle")
        builder.setPositiveButton("yes") { _, _ ->
            notesViewModel.deleteNote(note)
            Toast.makeText(this@MainActivity, "Note Deleted!!!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    //! on destroy
    override fun onDestroy() {
        super.onDestroy()
        if (binding != null) {
            binding = null
        }
    }
}