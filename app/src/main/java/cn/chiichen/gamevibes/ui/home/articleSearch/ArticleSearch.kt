package cn.chiichen.gamevibes.ui.home.articleSearch

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.ui.home.recommend.RecommendViewModel

@Composable
fun ArticleSearchScreen(navController: NavHostController,viewModel: ArticleSearchViewModel = viewModel()) {
    var searchText by viewModel.searchText
    var isExpanded by remember { mutableStateOf(false) }
    val searchHistory = listOf(
        "短文本",
        "较长的搜索记录文本1",
        "搜索记录",
        "非常长的搜索记录文本2超出长度",
        "搜索记录5",
        "搜索记录6",
        "搜索记录7",
        "搜索记录8",
        "搜索记录9",
        "搜索记录10",
        "较长的搜索记录文本1",
        "搜索记录",
        "非常长的搜索记录文本2超出长度",
        "搜索记录5"
    )
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Row {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回首页"
                )
            }
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text(text = "搜索") },
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    IconButton(
                        onClick = {
                            if (searchText.isNotEmpty()) {
                                //TODO 将searchText传给搜索页
                                navController.navigate("articleSearchResult")
                            }
                        },
                        enabled = searchText.isNotEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "搜索"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = if (searchText.isNotEmpty()) ImeAction.Search else ImeAction.None
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (searchText.isNotEmpty()) {
                            navController.navigate("articleSearchResult")
                        }
                    }
                ),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "搜索历史", fontWeight = FontWeight.Bold)

        val maxLines = if (isExpanded) 5 else 2
        val maxItems = maxLines * 4  // Assuming 4 items per row

        SearchHistorySection(searchHistory, maxItems, isExpanded) {
            isExpanded = !isExpanded
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "搜索发现",fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                for (i in 1..5) {
                    Text(text = "搜索发现 $i")
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                for (i in 6..10) {
                    Text(text = "搜索发现 $i")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "热门讨论")
    }
}


@Composable
fun SearchHistorySection(
    searchHistory: List<String>,
    maxItems: Int,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 20.dp
    val buttonPadding = 8.dp
    var currentRowWidth = 0.dp

    Column {
        var currentRowItems = mutableListOf<String>()
        val rows = mutableListOf<List<String>>()

        val itemsToShow = if (isExpanded) searchHistory else searchHistory.take(maxItems)

        for (item in itemsToShow) {
            val itemText = if (item.length > 7) "${item.take(7)}..." else item
            val itemWidth = itemText.length * 20.dp + buttonPadding  // Assuming 14.dp per character

            if (currentRowWidth + itemWidth > screenWidth) {
                rows.add(currentRowItems)
                currentRowItems = mutableListOf()
                currentRowWidth = 0.dp
            }

            currentRowItems.add(item)
            currentRowWidth += itemWidth
        }

        if (currentRowItems.isNotEmpty()) {
            rows.add(currentRowItems)
        }

        rows.forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                rowItems.forEach { record ->
                    Button(onClick = { /* TODO 点击逻辑 */ }, modifier = Modifier.padding(4.dp)) {
                        Text(text = if (record.length > 8) "${record.take(8)}..." else record)
                    }
                }
            }
        }

        if (searchHistory.size > maxItems && !isExpanded) {
            Button(onClick = onExpandToggle, modifier = Modifier.padding(4.dp)) {
                Text(text = "更多")
            }
        }
        if (isExpanded) {
            Button(onClick = onExpandToggle, modifier = Modifier.padding(4.dp)) {
                Text(text = "收起")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ASPrev(){
    val navController = rememberNavController()
    ArticleSearchScreen(navController = navController)
}
