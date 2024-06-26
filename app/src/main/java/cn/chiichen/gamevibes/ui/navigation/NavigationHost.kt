package cn.chiichen.gamevibes.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.chiichen.gamevibes.ui.common.article.ArticleDetailScreen
import cn.chiichen.gamevibes.ui.common.game.GameDetailScreen
import cn.chiichen.gamevibes.ui.common.game.GameReviewScreen
import cn.chiichen.gamevibes.ui.games.GamesScreen
import cn.chiichen.gamevibes.ui.games.GamesSearchScreen
import cn.chiichen.gamevibes.ui.games.GamesViewModel
import cn.chiichen.gamevibes.ui.home.HomeScreen
import cn.chiichen.gamevibes.ui.home.articleSearch.ArticleSearchResultScreen
import cn.chiichen.gamevibes.ui.home.articleSearch.ArticleSearchScreen
import cn.chiichen.gamevibes.ui.home.articleSearch.ArticleSearchViewModel
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
    modifier: Modifier = Modifier
) {
    val articleSearchViewModel: ArticleSearchViewModel = viewModel()
    val gamesViewModel: GamesViewModel = viewModel()
    NavHost(navController, startDestination = "splash", modifier = modifier) {
        composable("splash") { SplashScreen(navController) }
        composable("guide") { GuideScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("article/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if (id != null) ArticleDetailScreen(navController, id)
        }
        composable("articleSearch") { ArticleSearchScreen(navController, articleSearchViewModel) }
        composable("articleSearchResult") {
            ArticleSearchResultScreen(
                navController,
                articleSearchViewModel
            )
        }
        composable("games") { GamesScreen(navController, gamesViewModel) }
        composable("gamesSearch") { GamesSearchScreen(navController, gamesViewModel) }
        composable("gameDetail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if (id != null) GameDetailScreen(navController, id)
        }
        composable("gameReview/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            GameReviewScreen(navController, name)
        }
        composable("post") { PostScreen(navController) }
        composable("messages") { MessagesScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("setting") { SettingScreen(navController) }
        composable("login") { LoginWebview(navController) }
        composable("setting/language") { LanguageScreen(navController) }
    }
}





