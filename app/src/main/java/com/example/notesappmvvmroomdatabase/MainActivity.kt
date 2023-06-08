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

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesRvAdapter: NotesRvAdapter
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
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

    }

    private fun onNoteClick(note: Notes) {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        startActivity(intent)
    }

    private fun onDeleteNoteClick(note: Notes) {
        notesViewModel.deleteNote(note)
        Toast.makeText(this@MainActivity, "Note Deleted!!!", Toast.LENGTH_SHORT).show()
    }
}