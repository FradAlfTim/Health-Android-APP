package com.example.assignmentproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val note: String
)
