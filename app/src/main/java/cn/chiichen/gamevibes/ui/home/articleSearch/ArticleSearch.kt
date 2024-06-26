package cn.chiichen.gamevibes.ui.home.articleSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cn.chiichen.gamevibes.R

@Composable
fun ArticleSearchScreen(navController: NavHostController,viewModel: ArticleSearchViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getSearchFinding()
        viewModel.getHotTopics()
    }

    var searchText by remember {mutableStateOf(viewModel.searchText.value)}
    var maxRows by remember { mutableIntStateOf(2) }
    var isOverFlow by remember { mutableStateOf(false) }
    val searchHistory by viewModel.searchHistory.collectAsState()
    val searchFindings by viewModel.searchFindings.collectAsState()
    val hotTopics by viewModel.hotTopics.collectAsState()



    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
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
                onValueChange = {
                    searchText = it
                    viewModel.updateSearchText(it)
                },
                placeholder = { Text(text = "输入搜索内容") },
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    IconButton(
                        onClick = {
                            if (searchText.isNotEmpty()) {
                                viewModel.addSearchHistory(searchText)
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
                            viewModel.addSearchHistory(searchText)
                            navController.navigate("articleSearchResult")
                        }
                    }
                ),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            item {
                Text(text = "搜索历史", fontWeight = FontWeight.Bold)
            }

            item {
                LimitedFlowRow(
                    maxRows = maxRows,
                    onRowsExceededMax = {
                        isOverFlow = true
                    }
                ){
                    searchHistory.asReversed().forEach { item ->
                        Button(
                            onClick = {
                                viewModel.updateSearchText(item)
                                viewModel.addSearchHistory(item)
                                navController.navigate("articleSearchResult")
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xfff7f8f9))
                        ) {
                            Text(text = item)
                        }
                    }
                }
            }

            item{
                if(isOverFlow){
                    IconButton(
                        onClick = {
                            maxRows = if(maxRows == 5) 2 else 5
                        }
                    ) {
                        Icon(
                            imageVector =
                                if(maxRows == 2) Icons.Default.KeyboardArrowDown
                                else Icons.Default.KeyboardArrowUp,
                            contentDescription = ""
                        )
                    }
                }
            }

            item {Spacer(modifier = Modifier.height(20.dp)) }
            item {
                Text(text = "搜索发现", fontWeight = FontWeight.Bold)
                // 使用手动网格布局替代 LazyVerticalGrid
                val rowCount = (searchFindings.size + 1) / 2 // 计算行数，2列布局
                for (rowIndex in 0 until rowCount) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        for (colIndex in 0 until 2) {
                            val itemIndex = rowIndex * 2 + colIndex
                            if (itemIndex < searchFindings.size) {
                                val displayedText = if (searchFindings[itemIndex].length > 7) searchFindings[itemIndex].substring(0, 7) + "..." else searchFindings[itemIndex]
                                Text(
                                    text = displayedText,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                        .clickable(
                                            onClick = {
                                                viewModel.updateSearchText(searchFindings[itemIndex])
                                                viewModel.addSearchHistory(searchFindings[itemIndex])
                                                navController.navigate("articleSearchResult")
                                            }
                                        )
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                val gradientBrush = Brush.verticalGradient(
                    colors = listOf(colorResource(id = R.color.grey), Color.White),
                    startY = 0f,
                    endY = 300f
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                        .background(brush = gradientBrush)
                        .padding(30.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = "热门讨论", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(20.dp))
                        hotTopics.forEachIndexed { index, topic ->
                            val displayedTopic = if (topic.length > 15) topic.substring(0, 15) + "..." else topic
                            Text(
                                text = "${index + 1}  $displayedTopic",
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .clickable(onClick = {
                                        viewModel.updateSearchText(topic)
                                        viewModel.addSearchHistory(topic)
                                        navController.navigate("articleSearchResult")
                                    })
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LimitedFlowRow(
    modifier: Modifier = Modifier,
    maxRows: Int = 2,
    horizontalSpacing: Dp = 8.dp,
    verticalSpacing: Dp = 8.dp,
    onRowsExceededMax: () -> Unit,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        val rows = mutableListOf<List<Placeable>>()
        var currentRow = mutableListOf<Placeable>()
        var currentRowWidth = 0

        placeables.forEach { placeable ->
            if (currentRowWidth + placeable.width <= constraints.maxWidth) {
                currentRow.add(placeable)
                currentRowWidth += placeable.width + horizontalSpacing.toPx().toInt()
            } else {
                rows.add(currentRow)
                currentRow = mutableListOf(placeable)
                currentRowWidth = placeable.width + horizontalSpacing.toPx().toInt()
            }
        }

        if (currentRow.isNotEmpty()) {
            rows.add(currentRow)
            if(rows.size > 2){
                onRowsExceededMax()
            }
        }



        if (rows.size > maxRows) {
            rows.subList(maxRows, rows.size).clear()
        }


        val height = rows.sumOf { row ->
            row.maxOf { it.height }
        } + (rows.size - 1) * verticalSpacing.toPx().toInt()

        val layoutHeight = height.coerceIn(0, constraints.maxHeight)
        val layoutWidth = constraints.maxWidth.coerceAtLeast(0)

        layout(layoutWidth, layoutHeight) {
            var yOffset = 0

            rows.forEach { row ->
                var xOffset = 0

                row.forEach { placeable ->
                    placeable.placeRelative(x = xOffset, y = yOffset)
                    xOffset += placeable.width + horizontalSpacing.toPx().toInt()
                }

                yOffset += row.maxOf { it.height } + verticalSpacing.toPx().toInt()
            }
        }
    }
}

