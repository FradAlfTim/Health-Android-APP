package com.example.assignmentproject.main

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.assignmentproject.database.UserTable
import com.example.assignmentproject.database.UserTableViewModel


class NavigationViewModel(application: Application): AndroidViewModel(application) {
    var name: MutableState<String> = mutableStateOf("")
    private val userTableViewModel: UserTableViewModel = UserTableViewModel(application)
    val height: MutableState<Double> = mutableDoubleStateOf(0.0)
    val weight: MutableState<Double> = mutableDoubleStateOf(0.0)

    fun setName(newName: String) {
        name.value = newName
    }

    fun getUserByName(name: String): UserTable? {
        return userTableViewModel.getUserByName(name)
    }

    fun setHeight(newHeight: Double) {
        height.value=newHeight
    }

    fun setWeight(newWeight: Double) {
        weight.value=newWeight
    }
}