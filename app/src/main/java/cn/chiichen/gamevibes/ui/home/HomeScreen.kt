package cn.chiichen.gamevibes.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.chiichen.gamevibes.R


@Composable
fun HomeScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("推荐", "热点")
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "首页"
                )
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
    HomeScreen()
}