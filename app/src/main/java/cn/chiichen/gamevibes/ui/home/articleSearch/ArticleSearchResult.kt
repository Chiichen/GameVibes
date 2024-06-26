package cn.chiichen.gamevibes.ui.home.articleSearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.utils.timeConvertor
import coil.compose.rememberAsyncImagePainter

@Composable
fun ArticleSearchResultScreen(navController: NavHostController,viewModel:ArticleSearchViewModel) {
    val articles by viewModel.articles.collectAsState()
    var searchText by viewModel.searchText
    val listState = rememberLazyListState()
    var order = 0

    LaunchedEffect(Unit) {
        viewModel.clean()
        viewModel.getArticles(order)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ) {
        Row {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回"
                )
            }
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.updateSearchText(searchText)
                },
                placeholder = { Text(text = "搜索") },
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "搜索"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = if (searchText.isNotEmpty()) ImeAction.Search else ImeAction.None
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (searchText.isNotEmpty()) {
                            viewModel.addSearchHistory(searchText)
                            viewModel.updateSearchText(searchText)
                            viewModel.clean()
                            viewModel.getArticles(order)
                        }
                    }
                ),
                singleLine = true
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ScrollableTabRow(
                modifier = Modifier.width(100.dp),
                backgroundColor = Color.White,
                selectedTabIndex = 0,
                edgePadding = 0.dp // 移除边缘填充使标签对齐到开始位置
            ) {
                Tab(
                    selected = true,
                    onClick = { },
                    text = { Text("全部") }
                )
            }
            //排序方式
            IconButton(
                onClick = {
                    order = if(order == 0) 1 else 0
                    viewModel.clean()
                    viewModel.getArticles(order)
                },
                modifier = Modifier.padding(horizontal = 8.dp),
                content = {
                    Row {
                        Icon(Icons.Default.Menu, contentDescription = "Switch", tint = Color.Black) // 设置 tint 属性为图标着色
                        Text(if (order == 0) "热度" else "时间", color = Color.Black) // 设置文本颜色
                    }
                }
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(articles) {article ->
                RowItem(navController,article = article)
            }
        }
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex != null && lastVisibleItemIndex >= articles.size - 5
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            viewModel.getArticles(order)
        }
    }
}

@Composable
private fun RowItem(navController: NavHostController,article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(80.dp)
            .clickable(onClick = {
                navController.navigate("article/${article.id}")
            }),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .height(80.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.padding(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = timeConvertor(article.post_time) + " • " + article.type,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "${article.comments}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = rememberAsyncImagePainter(article.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(16 / 9f) // Aspect ratio of 16:9
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

