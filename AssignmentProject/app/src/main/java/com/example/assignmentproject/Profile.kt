package com.example.assignmentproject

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Profile(navController2: NavHostController, navViewModel: NavigationViewModel) {
    val height = navViewModel.height.value
    val weight = navViewModel.weight.value
    val userName = navViewModel.name.value
    var bmi by remember { mutableDoubleStateOf(0.0) }
    val lightSkyBlue = Color(0xFF87CEEB)
    val lightBlue = Color(0xFF00BCD4)

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
                        startY = 200f, // 设置渐变的起始位置
                        endY = 800f // 设置渐变的结束位置
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                // User profile picture section
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "User Avatar",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(width = 2.dp, color = Color.White, shape = CircleShape)
                    )
                    FloatingActionButton(
                        onClick = { /* Handle click */ },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(10.dp)
                    ) {
                        Text(text = "Edit")
                    }
                }

                // User profile details section
                Text(
                    text = "Profile Details",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 30.dp)
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp).clickable {  },
                    color = lightBlue
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "User Name: $userName", modifier = Modifier.weight(1f).padding(vertical = 14.dp))
                    }
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    color = lightBlue
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Weight: $weight", modifier = Modifier.weight(1f).padding(vertical = 14.dp))
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    color = lightBlue
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Height: $height", modifier = Modifier.weight(1f).padding(vertical = 14.dp))
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    color = lightBlue
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if(height != 0.0) {
                            bmi = weight / height / height
                        }
                        Text(text = "BMI: $bmi", modifier = Modifier.weight(1f).padding(vertical = 14.dp))
                    }
                }
            }
        }
    }
}