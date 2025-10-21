package com.example.appscontack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appscontack.ui.screen.AddEditContactScreen
import com.example.appscontack.ui.screen.ListContactScreen


object Routes {
    const val LIST_SCREEN = "list"
    const val ADD_EDIT_SCREEN = "add_edit"
    const val ARG_CONTACT_INDEX = "contactIndex"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.LIST_SCREEN
    ) {
        // Rute untuk ListContactScreen
        composable(Routes.LIST_SCREEN) {
            ListContactScreen(navController = navController)
        }


        composable(
            route = "${Routes.ADD_EDIT_SCREEN}?${Routes.ARG_CONTACT_INDEX}={${Routes.ARG_CONTACT_INDEX}}",
            arguments = listOf(
                navArgument(Routes.ARG_CONTACT_INDEX) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->

            val contactIndexString = backStackEntry.arguments?.getString(Routes.ARG_CONTACT_INDEX)
            AddEditContactScreen(
                navController = navController,
                contactIndex = contactIndexString?.toIntOrNull()
            )
        }
    }
}