package com.example.assignmentproject.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignmentproject.main.BarChartScreen
import com.example.assignmentproject.main.NavigationViewModel
import com.example.assignmentproject.main.PieChartScreen


@Composable
fun Home(navController2: NavHostController, navViewModel: NavigationViewModel) {
    val showChart = remember { mutableStateOf(false) }
    val showPieChart = remember { mutableStateOf(false) }
    val currentUser = navViewModel.getUserByName(navViewModel.name.value)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(text = "Welcome, ${currentUser?.name ?: ""}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.size(30.dp))
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFF91D8D0), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "Steps: ${currentUser?.steps}", style = MaterialTheme.typography.bodyLarge)
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFF91D8D0), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "Calories Burned: ${currentUser?.calories}", style = MaterialTheme.typography.bodyLarge)
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFF91D8D0), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "Sleep Duration: ${currentUser?.sleep}", style = MaterialTheme.typography.bodyLarge)
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFF91D8D0), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "Exercise Duration: ${currentUser?.exercise}", style = MaterialTheme.typography.bodyLarge)
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFF91D8D0), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "BMI: ${currentUser?.bmi}", style = MaterialTheme.typography.bodyLarge)
                }
            }
            item {
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = { showChart.value = true }
                ) {
                    Text("Sleeping time due 7days")
                }

                Button(
                    onClick = { showPieChart.value = true },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Exercise due 7days")
                }
            }
        }

        if (showChart.value) {
            BarChartScreen(onClose = { showChart.value = false })
        }
        if (showPieChart.value) {
            PieChartScreen(onClose = { showPieChart.value = false })
        }
    }
}