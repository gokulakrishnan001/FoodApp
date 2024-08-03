package com.example.foodapp

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodapp.database.MenuItemRoom
import com.example.foodapp.screens.ExpandFoodScreen
import com.example.foodapp.screens.Home
import com.example.foodapp.screens.OnBoard
import com.example.foodapp.screens.OnBoardScreen
import com.example.foodapp.screens.Profile

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(databaseMenuItem: List<MenuItemRoom>) {
    SharedTransitionLayout {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = OnBoard.route) {
            composable(Home.route) {
                Home(this,navController, databaseMenuItem)
            }
            composable(Profile.route) {
                Profile(navController)
            }
            composable(OnBoard.route) {
                OnBoardScreen(navController)
            }
            composable(
                "Expand_food/{image}/{title}/{description}/{price}/{category}",
                arguments = listOf(
                    navArgument("image") {
                        type = NavType.StringType
                    },
                    navArgument("price") {
                        type = NavType.StringType
                    },
                    navArgument("title") {
                        type = NavType.StringType
                    },
                    navArgument("description") {
                        type = NavType.StringType
                    },
                    navArgument("category") {
                        type = NavType.StringType
                    },
                )
            ) {
                val image = it.arguments?.getString("image") ?: ""
                val title = it.arguments?.getString("title") ?: ""
                val price = it.arguments?.getString("price") ?: ""
                val description = it.arguments?.getString("description") ?: ""
                val category = it.arguments?.getString("category") ?: ""
                ExpandFoodScreen(
                    image = image,
                    title = title,
                    description = description,
                    price = price,
                    category = category,
                    this,
                    navController
                )
            }
        }
    }
}