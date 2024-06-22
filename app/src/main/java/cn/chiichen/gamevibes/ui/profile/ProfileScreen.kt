package cn.chiichen.gamevibes.ui.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.ui.profile.favorite.FavoriteScreen
import cn.chiichen.gamevibes.ui.profile.like.LikeScreen
import cn.chiichen.gamevibes.ui.profile.post.PostScreen
import cn.chiichen.gamevibes.ui.profile.review.ReviewScreen

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = viewModel()) {
    if (viewModel.isLoggedIn) {
        LoggedInProfileScreen(navController, viewModel)
    } else {
        LoggedOutProfileScreen(navController)
    }
}

@Composable
fun LoggedOutProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "暂未登录", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "登录后可使用更多功能", color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("login") }) {
            Text(text = "登录")
        }
    }
}

@Composable
fun LoggedInProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header Image with Settings Icon
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // TODO: Replace with actual image
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            IconButton(
                onClick = {
                    navController.navigate("setting")
                }, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Rounded.Settings,
                    contentDescription = null, tint = Color.White
                )
            }
        }

        // Profile Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = viewModel.username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = viewModel.joinDate, color = Color.Gray)
                    Text(text = viewModel.location, color = Color.Gray)
                    Text(text = viewModel.id, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = viewModel.bio, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = viewModel.following.toString(), fontWeight = FontWeight.Bold)
                    Text(text = "关注", color = Color.Gray)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = viewModel.followers.toString(), fontWeight = FontWeight.Bold)
                    Text(text = "粉丝", color = Color.Gray)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = viewModel.likes.toString(), fontWeight = FontWeight.Bold)
                    Text(text = "获赞与收藏", color = Color.Gray)
                }
            }
        }
        var selectedTabIndex by remember { mutableStateOf(0) }
        // Tabs
        TabRow(selectedTabIndex = selectedTabIndex) {
            Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }) {
                Text(text = "帖子", modifier = Modifier.padding(16.dp))
            }
            Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }) {
                Text(text = "点评", modifier = Modifier.padding(16.dp))
            }
            Tab(selected = selectedTabIndex == 2, onClick = { selectedTabIndex = 2 }) {
                Text(text = "收藏", modifier = Modifier.padding(16.dp))
            }
            Tab(selected = selectedTabIndex == 3, onClick = { selectedTabIndex = 3 }) {
                Text(text = "点赞", modifier = Modifier.padding(16.dp))
            }
        }

        // Content
        when (selectedTabIndex) {
            0 -> PostScreen()
            1 -> ReviewScreen()
            2 -> FavoriteScreen()
            3 -> LikeScreen()
        }
    }
}