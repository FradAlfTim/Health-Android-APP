package com.example.assignmentproject.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignmentproject.main.NavigationViewModel
import com.example.assignmentproject.record.RecordsActivity
import com.example.assignmentproject.record.navigationItems
import com.example.assignmentproject.ui.ScreenTitleText
import com.example.assignmentproject.ui.rememberForResultLauncher


@Preview
@Composable
private fun PreviewNavigate() {
    Navigate(navController2 = rememberNavController(), navViewModel = viewModel())
}


@Composable
fun Navigate(navController2: NavHostController, navViewModel: NavigationViewModel) {
    val launcher = rememberForResultLauncher()

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 20.dp)
    ) {
        ScreenTitleText(title = "Navigation")
        Spacer(modifier = Modifier.size(30.dp))
        // Text("Add list of activities here.", style = MaterialTheme.typography.bodyLarge)
        LazyColumn {
            items(navigationItems) {
                NavigationItem(title = it.title()) {
                    launcher.launch(RecordsActivity.action(context = context, it))
                }
            }
        }
    }
}


@Composable
private fun NavigationItem(modifier: Modifier = Modifier, title: String, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(vertical = 10.dp)
            .clickable { onClick() }
            .background(Color.Gray, RoundedCornerShape(10.dp))
            .padding(20.dp, 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge, color = Color.White)
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "",
            tint = Color.White
        )
    }
}

