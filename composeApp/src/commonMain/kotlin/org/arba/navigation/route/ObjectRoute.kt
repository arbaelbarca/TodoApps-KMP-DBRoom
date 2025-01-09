package org.arba.navigation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ObjectRouteScreen(
    val route: String,
    var title: String,
    val defaultIcon: ImageVector
) {
    data object Home : ObjectRouteScreen(
        route = "HOME",
        title = "Home",
        defaultIcon = Icons.Filled.Home,
    )
    data object AddNewTask : ObjectRouteScreen(
        route = "NewTask",
        title = "NewTask",
        defaultIcon = Icons.Filled.Home,
    )
}