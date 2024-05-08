package com.example.assignmentproject


import android.app.Application
import kotlinx.coroutines.flow.Flow

class UserTableRepository (application: Application) {

    private var subjectDao: UserTableDAO =
        UserTableDatabase.getDatabase(application).UserTableDAO()

    val allUsers: Flow<List<UserTable>> = subjectDao.getAllUser()

    suspend fun insert(subject: UserTable) {
        subjectDao.insertUser(subject)
    }

    suspend fun delete(subject: UserTable) {
        subjectDao.deleteUser(subject)
    }

    suspend fun update(subject: UserTable) {
        subjectDao.updateUser(subject)
    }
}
