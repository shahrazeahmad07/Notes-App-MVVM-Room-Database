package com.example.notesappmvvmroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappmvvmroomdatabase.adapter.NotesRvAdapter
import com.example.notesappmvvmroomdatabase.databinding.ActivityMainBinding
import com.example.notesappmvvmroomdatabase.model.Notes
import com.example.notesappmvvmroomdatabase.view_model.NotesViewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var notesRvAdapter: NotesRvAdapter
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Setting Recycler View:
        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)

        //! setting View Model and creating adapter
        notesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NotesViewModel::class.java]
        notesViewModel.allNotes.observe(this, Observer { list ->
            list.let {
                notesRvAdapter = NotesRvAdapter(ArrayList(it), { note ->
                    onNoteClick(note)
                }, { note ->
                    onDeleteNoteClick(note)
                })
            }
        })

        binding?.floatingActionButton?.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onNoteClick(note: Notes) {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        startActivity(intent)
    }

    private fun onDeleteNoteClick(note: Notes) {
        notesViewModel.deleteNote(note)
        Toast.makeText(this@MainActivity, "Note Deleted!!!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (binding != null) {
            binding = null
        }
    }
}