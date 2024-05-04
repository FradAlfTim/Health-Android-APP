package com.example.assignmentproject

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NavigationViewModel: ViewModel() {

    val name:MutableState<String> = mutableStateOf("")
    val height: MutableState<Double> = mutableDoubleStateOf(0.0)
    val weight: MutableState<Double> = mutableDoubleStateOf(0.0)

    fun setName(newName: String) {
        name.value=newName
    }

    fun setHeight(newHeight: Double) {
        height.value=newHeight
    }

    fun setWeight(newWeight: Double) {
        weight.value=newWeight
    }
}