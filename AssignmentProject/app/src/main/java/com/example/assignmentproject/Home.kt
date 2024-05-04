package com.example.assignmentproject

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Home(navController2: NavHostController, navViewModel: NavigationViewModel) {

    val name = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home Screen",
                style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.size(30.dp))
            Text("Enter your name here", style =
            MaterialTheme.typography.bodyLarge)
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it
                    navViewModel.setName(name.value)
                },

                label = { Text("Name") }
            )
        }
    }
}