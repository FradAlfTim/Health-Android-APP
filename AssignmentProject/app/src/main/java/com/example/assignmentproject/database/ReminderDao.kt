package com.example.assignmentproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Insert
    suspend fun insert(user: Reminder): Long

    @Query("SELECT * FROM Reminder")
      fun getAll(): Flow<List<Reminder>>

    @Delete
    suspend fun delete(user: Reminder)

}
