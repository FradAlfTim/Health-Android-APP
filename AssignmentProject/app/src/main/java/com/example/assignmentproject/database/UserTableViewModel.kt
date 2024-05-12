package com.example.assignmentproject.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserTableViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: UserTableRepository
    init{
        cRepository = UserTableRepository(application)
    }
    val allUsers: LiveData<List<UserTable>> = cRepository.allUsers.asLiveData()

    fun getUserByName(name: String): UserTable? {
        return runBlocking {
            cRepository.getUserByName(name)
        }
    }
    fun insertSubject(subject: UserTable) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insert(subject)
    }
    fun updateSubject(subject: UserTable) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.update(subject)
    }
    fun deleteSubject(subject: UserTable) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.delete(subject)
    }
}
