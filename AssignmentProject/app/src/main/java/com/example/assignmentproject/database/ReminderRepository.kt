package com.example.assignmentproject.database

import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val database: ReminderDao) {

     fun getAllReminders(): Flow<List<Reminder>> {
        return database.getAll()
    }

    suspend fun insertReminder(reminder: Reminder) {
        database.insert(reminder)
    }

}