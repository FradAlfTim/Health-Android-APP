package com.example.assignmentproject.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun About(navController2: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "About Screen",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Our application for the health sector allows the user to enter body data and modify it according to the user's movement (e.g. height, weight, etc.). The application allows the user to enter physical data (e.g. height, weight, etc.) and uses the location feature to display the route the user has travelled during the day on a map and track their progress (e.g. how many steps they have walked). The application uses a diet and nutrition tracker to help users record their daily diet, calculate calorie and nutrient intake, and provide advice on healthy eating. It also allows users to schedule and manage their medical appointments and set reminders. The app also records the user's breathing rate through sensors while they sleep and calculates the quality of their sleep. \n",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}