package cn.chiichen.gamevibes.ui

import android.content.Context
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
fun MyApp(context: Context) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if(currentRoute == "home" || currentRoute == "games"
                || currentRoute == "messages" || currentRoute == "profile" ) {
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
