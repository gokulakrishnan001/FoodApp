package com.example.foodapp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.database.MenuItemRoom
import com.example.foodapp.screens.Home
import com.example.foodapp.screens.OnBoard
import com.example.foodapp.screens.OnBoardScreen
import com.example.foodapp.screens.Profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(databaseMenuItem: List<MenuItemRoom>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = OnBoard.route) {
        composable(Home.route) {
            Home(navController,databaseMenuItem)
        }
        composable(Profile.route) {
            Profile(navController)
        }
        composable(OnBoard.route) {
            OnBoardScreen(navController)
        }

    }
}