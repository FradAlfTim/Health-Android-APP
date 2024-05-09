package com.example.assignmentproject

import android.content.Intent
import android.util.Log
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
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // val LightSkyBlue = Color(0xFF87CEEB)

    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = { result -> user = result.user}, onAuthError = { user = null})
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
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Sign in with Google button
            Button(
                onClick = {
                    val gso = GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
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
        }else{
            user?.displayName?.let { navViewModel.setName(it) }
            val newUser = UserTable(
                name = navViewModel.name.value,
                password = password,
                type = "user",
                gender = "Others",
                weight = 0.0,
                height = 0.0
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

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {

    val scope = rememberCoroutineScope()

    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try{

            val account = task.getResult(ApiException::class.java)!!
            Log.d("GoogleAuth", "account $account")

            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }

        }catch (e: ApiException){
            Log.d("GoogleAuth", e.toString())
            onAuthError(e)
        }
    }
}