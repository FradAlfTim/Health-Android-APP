package com.example.assignmentproject

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class NavigationViewModel: ViewModel() {

    var name:MutableState<String> = mutableStateOf("")

    fun setName(newName: String) {
        name.value=newName
    }
}