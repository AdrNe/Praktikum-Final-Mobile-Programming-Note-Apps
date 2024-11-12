package com.example.noteappsfinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class NoteModel(
    var title: String,
    var description: String,
    //TODO 54 Menambahkan primary key yang di-generate secara otomatis.
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
