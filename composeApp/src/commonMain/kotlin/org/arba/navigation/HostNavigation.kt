package org.arba.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.arba.navigation.route.ObjectRouteScreen
import org.arba.ui.screen.home.HomeScreen
import org.arba.ui.screen.task.TaskAddNewScreen


@Composable
fun HostNavRoute() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ObjectRouteScreen.Home.route) {
        composable(ObjectRouteScreen.Home.route) { HomeScreen(navController) }
        composable(ObjectRouteScreen.AddNewTask.route) { TaskAddNewScreen(navController) }
    }
}