package com.example.assignmentproject.main.user

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.assignmentproject.database.UserTable
import com.example.assignmentproject.database.UserTableViewModel
import com.example.assignmentproject.main.NavigationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController1: NavHostController, navViewModel: NavigationViewModel, userTableViewModel: UserTableViewModel) {
    var userName by remember { mutableStateOf ("") }
    var password by remember { mutableStateOf ("") }
    var confirmPassword by remember { mutableStateOf ("") }
    val genders = listOf("Male", "Female", "Others")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(genders[0]) }
    val context = LocalContext.current
    val userTableViewModel: UserTableViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sign Up",
            // refers to a predefined text style provided by the Material Design theme. It typically
            // represents a large head line style suitable for headings.
            style = MaterialTheme.typography.headlineLarge,
            // Center and add vertical spacing
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 60.dp),
            textAlign = TextAlign.Center)

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .focusProperties {
                        canFocus = false
                    }
                    .padding(bottom = 18.dp),
                readOnly = true,
                value = selectedGender,
                onValueChange = {},
                label = { Text("Gender") },
                //manages the arrow icon up and down
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            )
            {
                genders.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedGender = selectionOption
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Button(
            onClick = {
                if(userName == "" || password == ""){
                    Toast.makeText(
                        context,
                        "You must fill in user name and password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }else if(userName.length < 2 || password.length < 7){
                    Toast.makeText(
                        context,
                        "The user name or password are too short.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else{
                    if(userTableViewModel.getUserByName(userName) == null && password == confirmPassword){
                        val newUser = UserTable(
                            name = userName,
                            password = password,
                            gender = selectedGender,
                            steps = "3745",
                            calories = "300",
                            sleep = "8h34m",
                            exercise = "2h47m",
                            bmi = "120"
                        )
                        userTableViewModel.insertSubject(newUser)
                        Toast.makeText(
                            context,
                            "Sign up successful, please login.",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController1.navigate("Login") {
                            // popUpTo is used to pop up to a given destination before navigating
                            popUpTo(navController1.graph.findStartDestination().id) {
                                saveState = true
                            }
                            //at most one copy of a given destination on the top of the back stack
                            launchSingleTop = true
                            // this navigation action should restore any state previously saved
                            restoreState = true
                        }
                    }else{
                        Toast.makeText(
                            context,
                            "Invalid user name or  password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(text= "Sign up", color= Color.White)
        }
    }
}