package cn.chiichen.gamevibes.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.chiichen.gamevibes.ui.games.GamesScreen
import cn.chiichen.gamevibes.ui.home.HomeScreen
import cn.chiichen.gamevibes.ui.login.LoginWebview
import cn.chiichen.gamevibes.ui.messages.MessagesScreen
import cn.chiichen.gamevibes.ui.post.PostScreen
import cn.chiichen.gamevibes.ui.profile.ProfileScreen
import cn.chiichen.gamevibes.ui.settings.SettingScreen
import cn.chiichen.gamevibes.ui.settings.language.LanguageScreen
import cn.chiichen.gamevibes.ui.splash.GuideScreen
import cn.chiichen.gamevibes.ui.splash.SplashScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    context: Context
) {
    NavHost(navController, startDestination = "splash", modifier = modifier) {
        composable("splash") { SplashScreen(navController = navController, context = context) }
        composable("guide") { GuideScreen(navController) }
        composable("home") { HomeScreen() }
        composable("games") { GamesScreen() }
        composable("post") { PostScreen() }
        composable("messages") { MessagesScreen() }
        composable("profile") { ProfileScreen(navController) }
        composable("setting") { SettingScreen(navController) }
        composable("login") { LoginWebview(navController) }
        composable("setting/language") { LanguageScreen(navController) }
    }
}





