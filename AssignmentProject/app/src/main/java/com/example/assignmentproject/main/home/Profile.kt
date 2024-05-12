package com.example.assignmentproject.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignmentproject.main.NavigationViewModel

@Composable
fun Profile(navController2: NavHostController, navViewModel: NavigationViewModel) {
    var height by remember { mutableDoubleStateOf(0.0) }
    var weight by remember { mutableDoubleStateOf(0.0) }
    val userName = navViewModel.name.value
    var bmi by remember { mutableDoubleStateOf(0.0) }
    val lightSkyBlue = Color(0xFF87CEEB)

    Surface(
        modifier = Modifier.fillMaxSize()
        //color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(lightSkyBlue, Color.White), // 设置渐变色
                        startY = 200f, // Set the starting position of the gradient
                        endY = 800f // Set the end position of the gradient
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = "Profile",
                    // refers to a predefined text style provided by the Material Design theme. It typically
                    // represents a large head line style suitable for headings.
                    style = MaterialTheme.typography.headlineLarge,
                    // Center and add vertical spacing
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 26.dp),
                    textAlign = TextAlign.Center)

                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "User Avatar",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(width = 2.dp, color = Color.White, shape = CircleShape),
                    )
                }

                // User profile details section
                Text(
                    text = "Profile Details",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 30.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "User Name:  $userName", modifier = Modifier.weight(1f).padding(vertical = 14.dp))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = weight.toString(),
                        onValueChange = { newWeight:String -> weight = newWeight.toDoubleOrNull() ?: weight },
                        label = { Text("Weight (kg)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 18.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = height.toString(),
                        onValueChange = { newHeight:String -> height = newHeight.toDoubleOrNull() ?: height },
                        label = { Text("Height (Meters)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 18.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if(height != 0.0) {
                        bmi = weight / height / height
                    }
                    val formattedBMI = String.format("%.2f", bmi)
                    Text(text = "BMI:  $formattedBMI", modifier = Modifier.weight(1f).padding(vertical = 14.dp))
                }
            }
        }
    }
}