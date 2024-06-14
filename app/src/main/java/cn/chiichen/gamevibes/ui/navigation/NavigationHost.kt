package cn.chiichen.gamevibes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.chiichen.gamevibes.ui.common.games.GamesScreen
import cn.chiichen.gamevibes.ui.common.home.HomeScreen
import cn.chiichen.gamevibes.ui.common.messages.MessagesScreen
import cn.chiichen.gamevibes.ui.common.profile.ProfileScreen


@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen() }
        composable("games") { GamesScreen() }
        composable("messages") { MessagesScreen()}
        composable("profile"){ ProfileScreen() }
    }
}

