package com.example.assignmentproject

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class NavigationViewModel(application: Application): AndroidViewModel(application) {
    var name: MutableState<String> = mutableStateOf("")
    private val userTableViewModel: UserTableViewModel = UserTableViewModel(application)

    fun setName(newName: String) {
        name.value = newName
    }

    fun getUserByName(name: String): UserTable? {
        return userTableViewModel.getUserByName(name)
    }
}