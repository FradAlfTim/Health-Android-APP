package com.example.assignmentproject

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class NavigationViewModel: ViewModel() {

    var name:MutableState<String> = mutableStateOf("")

    fun setName(newName: String) {
        name.value=newName
    }
}