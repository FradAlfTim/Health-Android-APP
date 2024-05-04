package com.example.assignmentproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun Login(navController1: NavHostController, navViewModel: NavigationViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val LightSkyBlue = Color(0xFF87CEEB)
    var isLoginSeccess by remember{mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Log in",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Sign in with Google button
        Button(
            onClick = {},
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Sign in with Google")
        }

        // Or section
        Row(modifier = Modifier.fillMaxWidth()) {
            // left side divider
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )

            // text
            Text(
                text = "Or",
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), // Transparency reduced to 50%
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // right side divider
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }

        // Username field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Log in button
        Button(
            onClick = {
                isLoginSeccess=true
                navController1.navigate("BottomNavigationBar") {
                    // popUpTo is used to pop up to a given destination before navigating
                    popUpTo(navController1.graph.findStartDestination().id) {
                        saveState = true
                    }
                    //at most one copy of a given destination on the top of the back stack
                    launchSingleTop = true
                    // this navigation action should restore any state previously saved
                    restoreState = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Log in")
        }

        Text(
            text = "Don't have a account? Create a new one.",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {
                    navController1.navigate("SignUp") {
                        // popUpTo is used to pop up to a given destination before navigating
                        popUpTo(navController1.graph.findStartDestination().id) {
                            saveState = true
                        }
                        //at most one copy of a given destination on the top of the back stack
                        launchSingleTop = true
                        // this navigation action should restore any state previously saved
                        restoreState = true
                    }
                }
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}