package com.example.assignmentproject.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignmentproject.database.UserTableViewModel
import com.example.assignmentproject.main.user.Login
import com.example.assignmentproject.main.user.SignUp
import com.example.assignmentproject.record.reminder.SetupReminderWorker
import com.example.assignmentproject.ui.theme.AssignmentProjectTheme

class MainActivity : ComponentActivity() {
    private val userTableViewModel: UserTableViewModel by viewModels()
    private val viewModel: NavigationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginNavigation(viewModel, userTableViewModel)
                }
            }
        }
        SetupReminderWorker.action(this)
    }
}

@Composable
fun LoginNavigation(navViewModel: NavigationViewModel, userTableViewModel: UserTableViewModel) {
    val navController1 = rememberNavController()

    NavHost(
        navController1,
        startDestination = "Login",
        modifier = Modifier
    ) {
        composable("Login") {
            Login(navController1, navViewModel, userTableViewModel)
        }
        composable("SignUp") {
            SignUp(navController1, navViewModel, userTableViewModel)
        }
        composable("BottomNavigationBar") {
            BottomNavigationBar(navController1, navViewModel)
        }
    }
}