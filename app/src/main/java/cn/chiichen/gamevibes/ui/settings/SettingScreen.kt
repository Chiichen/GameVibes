package cn.chiichen.gamevibes.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SettingScreen(navController: NavHostController) {

    Column {
        Row(modifier = Modifier.clickable {
            navController.navigate("home") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }) {
            Icon(Icons.Rounded.AccountCircle, contentDescription = "编辑资料")
            Text(text = "编辑资料")
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null)
        }
        Row(modifier = Modifier.clickable {
            navController.navigate("setting/language") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }) {
            Icon(Icons.Rounded.Create, contentDescription = "切换语言")
            Text(text = "切换语言")
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null)
        }
    }

}


@Preview
@Composable
fun SettingScreenPreview() {
    val mockNavController = rememberNavController()
    SettingScreen(mockNavController)
}