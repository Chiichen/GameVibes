package cn.chiichen.gamevibes.ui.profile


import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(navController: NavController) {
    Icon(Icons.Rounded.Settings, contentDescription = "Setting", modifier = Modifier.clickable {
        navController.navigate("setting") {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    })
}
