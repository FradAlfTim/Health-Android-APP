package com.example.assignmentproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignmentproject.ui.theme.AssignmentProjectTheme

class MainActivity : ComponentActivity() {
    private val viewModel: NavigationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginNavigation(viewModel)
                }
            }
        }
    }
}

@Composable
fun LoginNavigation(navViewModel: NavigationViewModel){
    val navController1 = rememberNavController()

    NavHost(
        navController1,
        startDestination = "Login",
    ) {
        composable("Login") {
            Login(navController1, navViewModel)
        }
        composable("SignUp") {
            SignUp(navController1, navViewModel)
        }
        composable("BottomNavigationBar") {
            BottomNavigationBar(navController1, navViewModel)
        }

    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssignmentProjectTheme {

    }
}*/