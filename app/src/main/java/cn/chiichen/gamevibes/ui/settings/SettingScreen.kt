package cn.chiichen.gamevibes.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R

@Composable
fun SettingScreen(navController: NavController, viewModel: SettingViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top Bar
        TopAppBar(
            title = { Text("设置") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack();
                }) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            backgroundColor = Color.White,
            elevation = 0.dp
        )

        // Profile Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = viewModel.username,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = viewModel.userId,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        // Settings Options
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 8.dp)
        ) {
            SettingOption(
                icon = R.drawable.ic_user, // 替换为你的编辑图标资源
                title = "编辑资料",
                onClick = { /*TODO: Handle edit profile click*/ }
            )
            SettingOption(
                icon = R.drawable.ic_password, // 替换为你的密码图标资源
                title = "修改密码",
                onClick = { /*TODO: Handle change password click*/ }
            )
            SettingOption(
                icon = R.drawable.ic_info, // 替换为你的信息图标资源
                title = "关于我们",
                onClick = { /*TODO: Handle about us click*/ }
            )
            SettingOption(
                icon = R.drawable.ic_logout, // 替换为你的退出图标资源
                title = "退出账号",
                onClick = { /*TODO: Handle logout click*/ }
            )
        }
    }
}

@Composable
fun SettingOption(icon: Int, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.AutoMirrored.Rounded.KeyboardArrowRight,//TODO: 替换为你的箭头图标资源
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun SettingScreenPreview() {
    val mockNavController = rememberNavController()
    SettingScreen(mockNavController)
}

//@Composable
//fun SettingScreen(navController: NavHostController) {
//
//    Column {
//        Row(modifier = Modifier.clickable {
//            navController.navigate("home") {
//                popUpTo(navController.graph.startDestinationId)
//                launchSingleTop = true
//            }
//        }) {
//            Icon(Icons.Rounded.AccountCircle, contentDescription = "编辑资料")
//            Text(text = "编辑资料")
//            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null)
//        }
//        Row(modifier = Modifier.clickable {
//            navController.navigate("setting/language") {
//                popUpTo(navController.graph.startDestinationId)
//                launchSingleTop = true
//            }
//        }) {
//            Icon(Icons.Rounded.Create, contentDescription = "切换语言")
//            Text(text = "切换语言")
//            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null)
//        }
//    }
//
//}
//
//
//@Preview
//@Composable
//fun SettingScreenPreview() {
//    val mockNavController = rememberNavController()
//    SettingScreen(mockNavController)
//}