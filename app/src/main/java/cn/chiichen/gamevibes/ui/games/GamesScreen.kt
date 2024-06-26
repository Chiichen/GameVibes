package cn.chiichen.gamevibes.ui.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.model.entities.Game
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun GamesScreen(navController: NavHostController, viewModel: GamesViewModel) {
    val games by viewModel.games.collectAsState()
    var searchText by remember { mutableStateOf(viewModel.searchText.value) }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.loadGames()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.background(colorResource(id = R.color.grey))
        ) {
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = "游戏", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScrollableTabRow(
                    modifier = Modifier.width(100.dp),
                    backgroundColor = colorResource(id = R.color.grey),
                    selectedTabIndex = 0,
                    edgePadding = 0.dp,
                    tabs = {
                        Tab(
                            selected = true,
                            onClick = { },
                            text = { Text("榜单") }
                        )
                    },
                )
                TextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        viewModel.updateSearchText(it)
                    },
                    singleLine = true,
                    placeholder = { Text(text = "搜索...") }
                )
                IconButton(
                    onClick = {
                        viewModel.updateSearchText(searchText)
                        navController.navigate("gamesSearch")
                  },
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(games) { index, item ->
                RowItem(navController, index, item)
            }
        }
    }

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex != null && lastVisibleItemIndex >= games.size - 5
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            viewModel.loadGames()
        }
    }
}

@Composable
private fun RowItem(navController: NavController, index: Int, game: Game) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(80.dp)
            .clickable(onClick = {
                navController.navigate("gameDetail/${game.id}")
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${index + 1}",
            modifier = Modifier.width(30.dp),
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = rememberAsyncImagePainter(
                model = game.image,
                placeholder = painterResource(R.drawable.image4),
                error = painterResource(R.drawable.image4)
            ),
            contentDescription = "",
            modifier = Modifier.size(80.dp).padding(5.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = game.name)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = ""
                )
                Text(text = "${game.score}")
            }
        }
    }
}
