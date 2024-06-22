package cn.chiichen.gamevibes.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.ui.home.hot.Hot
import cn.chiichen.gamevibes.ui.home.recommend.Recommend


@Composable
fun HomeScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("推荐", "热点")
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "首页"
                )
            },
            actions = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            // 点击整个输入框区域的点击事件
                            navController.navigate("articleSearch")
                        }
                ) {
                    TextField(
                        value = "",
                        onValueChange = { /* 处理输入框内容变化 */ },
                        placeholder = { Text(text = "搜索") },
                        modifier = Modifier
                            .width(200.dp)
                            .background(Color.White),
                        trailingIcon = null, // 移除内部的点击逻辑
                        singleLine = true,
                        enabled = false // 禁用输入框但允许点击
                    )
                    IconButton(
                        onClick = {
                        // 点击搜索图标的点击事件
                        navController.navigate("articleSearch")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "搜索"
                        )
                    }
                }
            },
            backgroundColor = colorResource(id = R.color.grey),
            contentColor = Color.Black
        )
        ScrollableTabRow(
            backgroundColor = colorResource(id = R.color.grey),
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp, // Remove edge padding to align tabs to the start
            tabs = {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 4.dp
                )
            }
        )
        when (selectedTabIndex) {
            0 -> Recommend()
            1 -> Hot()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    val navController = rememberNavController()
    HomeScreen(navController)
}