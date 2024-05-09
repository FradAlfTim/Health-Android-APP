package com.example.assignmentproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController1: NavHostController, navViewModel: NavigationViewModel, userTableViewModel: UserTableViewModel) {
    var userName by remember { mutableStateOf ("") }
    var password by remember { mutableStateOf ("") }
    var confirmPassword by remember { mutableStateOf ("") }
    val genders = listOf("Male", "Female", "Others")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(genders[0]) }
    var weight by remember { mutableDoubleStateOf (0.0) }
    var hight by remember { mutableDoubleStateOf (0.0) }
    var isSignUpSeccess by remember {mutableStateOf(false)}
    val userTableViewModel: UserTableViewModel = viewModel()

    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()) //add vertical scrolling
                .padding(36.dp)
        ) {
            Text(text = "Registration",
                // refers to a predefined text style provided by the Material Design theme. It typically
                // represents a large head line style suitable for headings.
                style = MaterialTheme.typography.headlineLarge,
                // Center and add vertical spacing
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp),
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
            OutlinedTextField(
                value = weight.toString(),
                onValueChange = { newWeight:String -> weight = newWeight.toDoubleOrNull() ?: weight },
                label = { Text("Weight (kg)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            )
            OutlinedTextField(
                value = hight.toString(),
                onValueChange = { newHight:String -> hight = newHight.toDoubleOrNull() ?: hight },
                label = { Text("Hight (Meters)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            )
            Button(
                onClick = {
                    val newUser = UserTable(
                        name = userName,
                        password = password,
                        type = "user",
                        gender = selectedGender,
                        weight = weight,
                        height = hight
                    )
                    userTableViewModel.insertSubject(newUser)
                    isSignUpSeccess=true
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
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Sign up")
            }
        }
    }
}