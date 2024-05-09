package com.example.assignmentproject


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
// This entity class will be mapped to a table named Subject with two columns (uid, name) in the
// underlying SQLite database of Room.
data class UserTable(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val name: String,
    val password: String,
    val gender: String,
    var steps: String,
    var calories: String,
    var sleep: String,
    var exercise: String,
    var bmi: String

)