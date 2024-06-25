package cn.chiichen.gamevibes.ui.splash

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import cn.chiichen.gamevibes.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, context: Context) {
    // 显示启动页内容，可以是一个简单的Logo或者动画
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
    }

    // 延迟一段时间后进行导航
    LaunchedEffect(Unit) {
        delay(2000) // 延迟2秒
        val destination = if (isFirstLaunch(context)) "guide" else "home"
        navController.navigate(destination) {
            popUpTo("splash") { inclusive = true }
        }
    }
}

fun isFirstLaunch(context: Context): Boolean {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val isFirstLaunch = sharedPreferences.getBoolean("is_first_launch", true)
    if (isFirstLaunch) {
        sharedPreferences.edit().putBoolean("is_first_launch", false).apply()
    }
    return isFirstLaunch
}
