package com.example.assignmentproject.main.user

import com.example.assignmentproject.R
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.assignmentproject.database.UserTable
import com.example.assignmentproject.database.UserTableViewModel
import com.example.assignmentproject.main.NavigationViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun Login(navController1: NavHostController, navViewModel: NavigationViewModel, userTableViewModel: UserTableViewModel) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Define a variable to hold the current user
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    // Remember launcher for Firebase authentication
    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = { result -> user = result.user}, onAuthError = { user = null})
    // Retrieve Google client ID from resources
    val token = stringResource(id = R.string.client_id)

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(user == null){
            Text(
                text = "Log in",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 66.dp)
            )

            // Sign in with Google button
            Button(
                onClick = {
                    // Configure Google sign-in options
                    val gso = GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()

                    // Create Google sign-in client
                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    // Launch sign-in intent
                    launcher.launch(googleSignInClient.signInIntent)
                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .height(50.dp)
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google logo",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = "Sign in with Google",
                    modifier = Modifier.padding(1.dp),
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    letterSpacing = 0.1.em
                )
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
                value = userName,
                onValueChange = { userName = it },
                label = { Text("User Name") },
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
                    if(userName != "" && password != ""){
                        val retrivedUser = userTableViewModel.getUserByName(userName)
                        if(retrivedUser == null || retrivedUser.password != password){
                            Toast.makeText(
                                context,
                                "The user cannot be found or the password is incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            navViewModel.setName(userName)
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
                        }
                    }else{
                        Toast.makeText(
                            context,
                            "Invalid user name or password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(
                    text ="Log in",
                    color = Color.White
                )
            }

            Text(
                text = "Don't have a account? Create a new one.",
                color = Color.Black,
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
        }else{
            user?.displayName?.let { navViewModel.setName(it) }
            val newUser = UserTable(
                name = navViewModel.name.value,
                password = password,
                gender = "Others",
                steps = "3745",
                calories = "300",
                sleep = "8h34m",
                exercise = "2h47m",
                bmi = "120"
            )
            userTableViewModel.insertSubject(newUser)
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
        }
    }
}

// Function to create a launcher for Firebase authentication
@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    // Remember coroutine scope
    val scope = rememberCoroutineScope()

    // Return a launcher for activity result
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        // Get signed-in account from the result
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try{
            // Get signed-in account from the result
            val account = task.getResult(ApiException::class.java)!!
            Log.d("GoogleAuth", "account $account")

            // Get Google sign-in credentials
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                // Sign in with Firebase using Google credentials
                val authResult = Firebase.auth.signInWithCredential(credential).await()

                // Call the callback for successful authentication
                onAuthComplete(authResult)
            }
        }catch (e: ApiException){
            Log.d("GoogleAuth", e.toString())
            onAuthError(e)
        }
    }
}