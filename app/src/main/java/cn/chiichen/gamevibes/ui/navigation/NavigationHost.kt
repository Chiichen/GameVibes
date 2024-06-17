package cn.chiichen.gamevibes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.chiichen.gamevibes.ui.games.GamesScreen
import cn.chiichen.gamevibes.ui.home.HomeScreen
import cn.chiichen.gamevibes.ui.messages.MessagesScreen
import cn.chiichen.gamevibes.ui.profile.ProfileScreen
import cn.chiichen.gamevibes.ui.settings.SettingScreen
import cn.chiichen.gamevibes.ui.settings.language.LanguageScreen


@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen() }
        composable("games") { GamesScreen() }
        composable("messages") { MessagesScreen() }
        composable("profile") { ProfileScreen(navController) }
        composable("setting") { SettingScreen(navController) }
        composable("setting/language") { LanguageScreen(navController) }
    }
}

//fun NavGraphBuilder.profileGraph(navController: NavController) {
//    navigation(startDestination = "", route = "profilePage") {
//        composable("profile") {  }
//        composable("setting") { ... }
//        composable("language") { ... }
//    }
//}



