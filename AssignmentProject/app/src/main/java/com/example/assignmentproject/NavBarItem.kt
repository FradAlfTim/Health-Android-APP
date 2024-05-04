package com.example.assignmentproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem (
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    fun navBarItems(): List<NavBarItem> {
        return listOf(
            NavBarItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Routes.Home.value
            ),
            NavBarItem(
                label = "Profile",
                icon = Icons.Filled.Person,
                route = Routes.Profile.value
            ),
            NavBarItem(
                label = "Navigate",
                icon = Icons.Filled.Search,
                route = Routes.Navigate.value
            ),
            NavBarItem(
                label = "About",
                icon = Icons.Filled.Star,
                route = Routes.About.value
            ),
        )
    }
}