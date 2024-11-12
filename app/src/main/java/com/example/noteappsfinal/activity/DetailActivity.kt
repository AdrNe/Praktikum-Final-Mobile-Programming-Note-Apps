package com.example.noteappsfinal.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteappsfinal.R
import com.example.noteappsfinal.database.MyDatabase
import com.example.noteappsfinal.databinding.ActivityDetailBinding
import com.example.noteappsfinal.model.NoteModel
import java.util.concurrent.Executors

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var database: MyDatabase
    private var title = ""
    private var description = ""
    var idNote = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idNote = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title") ?: ""
        description = intent.getStringExtra("description") ?: ""

        binding.detailTitleNoteTextView.text = title
        binding.detailNoteDescriptionTextView.text = description
        database = MyDatabase.getDatabase(this)

        if (title.isEmpty() && description.isEmpty()) {
            binding.detailTitleNoteTextView.text = getString(R.string.no_data_found)
            binding.detailNoteDescriptionTextView.visibility = View.GONE
        }
    }

    fun editData(view: View?) {
        val intent = Intent(this, EditNoteActivity::class.java).apply {
            putExtra("id", idNote)
            putExtra("title", title)
            putExtra("description", description)
        }
        startActivity(intent)
    }

    fun deleteData(view: View) {
        val noteData = NoteModel(title, description).apply {
            id = idNote
        }
        Executors.newSingleThreadExecutor().execute {
            database.noteDao().delete(noteData)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        Toast.makeText(this, getString(R.string.data_successfully_deleted), Toast.LENGTH_SHORT).show()
    }
}
