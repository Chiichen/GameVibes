package cn.chiichen.gamevibes.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.ui.navigation.BottomNavigationBar
import cn.chiichen.gamevibes.ui.navigation.NavigationHost

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentRoute) {
                            "home" -> "首页"
                            "games" -> "游戏"
                            "messages" -> "消息"
                            "profile" -> "我的"
                            else -> "应用程序"
                        }
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
