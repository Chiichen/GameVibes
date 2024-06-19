package cn.chiichen.gamevibes.ui

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.ui.navigation.BottomNavigationBar
import cn.chiichen.gamevibes.ui.navigation.NavigationHost

@Composable
fun MyApp(context: Context) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if(currentRoute == "games" || currentRoute == "messages"|| currentRoute == "profile"){
                TopAppBar(
                    title = {
                        Text(
                            text = when (currentRoute) {
                                "games" -> "游戏"
                                "messages" -> "消息"
                                "profile" -> "我的"
                                else -> "应用程序"
                            }
                        )
                    },
                    backgroundColor = colorResource(id = R.color.grey),
                    contentColor = Color.Black
                )
            }
        },
        bottomBar = {
            if(currentRoute != "guide" && currentRoute != "splash") {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavigationHost(navController = navController,
            modifier = Modifier.padding(innerPadding),
            context = context
        )
    }
}
