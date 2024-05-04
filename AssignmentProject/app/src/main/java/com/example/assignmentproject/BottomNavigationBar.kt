package com.example.assignmentproject

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(navController1: NavHostController,  navViewModel: NavigationViewModel) {
    val navController2 = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation (backgroundColor= Color.LightGray ){
                val navBackStackEntry by navController2.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavBarItem().navBarItems().forEach { navItem ->
                    BottomNavigationItem(
                        icon = { Icon(navItem.icon, contentDescription =
                        null) },
                        label = { Text(navItem.label) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == navItem.route
                        } == true,
                        onClick = {
                            navController2.navigate(navItem.route) {
                                // popUpTo is used to pop up to a given destination before navigating
                                popUpTo(navController2.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                //at most one copy of a given destination on the top of the back stack
                                launchSingleTop = true
                                // this navigation action should restore any state previously saved
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController2,
            startDestination = Routes.Home.value,
            Modifier.padding(paddingValues)
        ) {
            composable(Routes.Home.value) {
                Home(navController2, navViewModel)
            }
            composable(Routes.Profile.value) {
                Profile(navController2, navViewModel)
            }
            composable(Routes.Navigate.value) {
                Navigate(navController2, navViewModel)
            }
            composable(Routes.About.value) {
                About(navController2)
            }
        }
    }
}