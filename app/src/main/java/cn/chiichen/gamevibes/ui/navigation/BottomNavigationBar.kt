package cn.chiichen.gamevibes.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.grey),
        contentColor = Color.Black
    ) {
        BottomNavigationItem(
            icon = {
                Image(
                    painter = painterResource(id = if(currentRoute == "home") R.drawable.ic_home_click else R.drawable.ic_home),
                    contentDescription = "home",
                    modifier = Modifier.size(50.dp))
            },
            label = { Text("首页", color = colorResource(id = if(currentRoute == "home") R.color.black else R.color.grey_200))},
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(10.dp)
        )
        BottomNavigationItem(
            icon = {
                Image(
                    painterResource(id = if(currentRoute == "games") R.drawable.ic_game_click else R.drawable.ic_game),
                    contentDescription = "games",
                    modifier = Modifier.size(50.dp)) },
            label = { Text("游戏", color = colorResource(id = if(currentRoute == "games") R.color.black else R.color.grey_200))},
            selected = currentRoute == "games",
            onClick = {
                navController.navigate("games") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(10.dp)
        )
        BottomNavigationItem(
            icon = {
                Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "",
                modifier = Modifier.size(70.dp)
                )
           },
            selected = currentRoute == "messages",
            onClick = {
                navController.navigate("post") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically)
        )
        BottomNavigationItem(
            icon = {
                Image(
                    painterResource(id = if(currentRoute == "messages") R.drawable.ic_message_click else R.drawable.ic_message),
                    contentDescription = "messages",
                    modifier = Modifier.size(50.dp)
                )
            },
            label = { Text("消息", color = colorResource(id = if(currentRoute == "messages") R.color.black else R.color.grey_200)) },
            selected = currentRoute == "messages",
            onClick = {
                navController.navigate("messages") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(10.dp)
        )
        BottomNavigationItem(
            icon = {
                Image(
                    painterResource(id = if(currentRoute == "profile") R.drawable.ic_profile_click else R.drawable.ic_profile),
                    contentDescription = "profile",
                    modifier = Modifier.size(50.dp)
                )
           },
            label = { Text("我的", color = colorResource(id = if(currentRoute == "profile") R.color.black else R.color.grey_200))},
            selected = currentRoute == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ppp(){
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
}
