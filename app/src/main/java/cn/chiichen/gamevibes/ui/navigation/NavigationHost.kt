package cn.chiichen.gamevibes.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.chiichen.gamevibes.ui.common.article.ArticleDetailScreen
import cn.chiichen.gamevibes.ui.games.GamesScreen
import cn.chiichen.gamevibes.ui.splash.GuideScreen
import cn.chiichen.gamevibes.ui.home.HomeScreen
import cn.chiichen.gamevibes.ui.home.articleSearch.ArticleSearchResultScreen
import cn.chiichen.gamevibes.ui.home.articleSearch.ArticleSearchScreen
import cn.chiichen.gamevibes.ui.messages.MessagesScreen
import cn.chiichen.gamevibes.ui.post.PostScreen
import cn.chiichen.gamevibes.ui.profile.ProfileScreen
import cn.chiichen.gamevibes.ui.settings.SettingScreen
import cn.chiichen.gamevibes.ui.settings.language.LanguageScreen
import cn.chiichen.gamevibes.ui.splash.SplashScreen


@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier, context: Context) {
    NavHost(navController, startDestination = "splash", modifier = modifier) {
        composable("splash"){ SplashScreen(navController = navController, context = context)}
        composable("guide"){GuideScreen(navController)}
        composable("home") { HomeScreen(navController) }
        composable("article") { ArticleDetailScreen(navController) }
        composable("articleSearch") { ArticleSearchScreen(navController) }
        composable("articleSearchResult") {ArticleSearchResultScreen(navController)}
        composable("games") { GamesScreen(navController) }
        composable("post") { PostScreen(navController) }
        composable("messages") { MessagesScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("setting") { SettingScreen(navController) }
        composable("setting/language") { LanguageScreen(navController) }
    }
}





