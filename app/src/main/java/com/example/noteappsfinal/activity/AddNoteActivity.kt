package com.example.noteappsfinal.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.noteappsfinal.R
import com.example.noteappsfinal.database.MyDatabase
import com.example.noteappsfinal.databinding.ActivityAddNoteBinding
import com.example.noteappsfinal.model.NoteModel
import java.util.concurrent.Executors

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var database: MyDatabase

    var title = ""
    var description = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        binding.activity = this
        database = MyDatabase.getDatabase(this)
    }

    private fun validateUsername(): Boolean {
        return if (binding.addTitleNote.text.toString().trim().isEmpty()) {
            binding.addTitleNoteTextInputLayout.error = getString(R.string.title_empty_error)
            binding.addTitleNote.requestFocus()
            false
        } else {
            binding.addTitleNoteTextInputLayout.isErrorEnabled = false
            true
        }
    }

    private fun validateDescription(): Boolean {
        return if (binding.addNoteDescription.text.toString().trim().isEmpty()) {
            binding.addNoteDescriptionTextInputLayout.error = getString(R.string.description_empty_error)
            binding.addNoteDescription.requestFocus()
            false
        } else {
            binding.addNoteDescriptionTextInputLayout.isErrorEnabled = false
            true
        }
    }

    fun saveData(view: View?) {
        title = binding.addTitleNote.text.toString().trim()
        description = binding.addNoteDescription.text.toString().trim()

        val isTitleValid = validateUsername()
        val isDescriptionValid = validateDescription()

        if (isTitleValid && isDescriptionValid) {
//            Log.d("tes data", "$title $description")
            if (title.isNotEmpty() && description.isNotEmpty()) {
                val noteData = NoteModel(title, description)
                Executors.newSingleThreadExecutor().execute {
                    database.noteDao().insertData(noteData)
                }
                Toast.makeText(this, R.string.data_successfully_save, Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
