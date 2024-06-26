package cn.chiichen.gamevibes.ui.common.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.ui.common.carousel.Carousel
import cn.chiichen.gamevibes.utils.timeConvertor
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest


@Composable
fun ArticleDetailScreen(navController: NavHostController, id: String, viewModel: ArticleDetailViewModel = ArticleDetailViewModel(id.toLong())) {
    val articleDetail by viewModel.articleDetail.collectAsState()
    val comments by viewModel.comments.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.grey),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "正文")
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { }) { }//对齐用
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp() // 返回上一页
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White,
                elevation = 8.dp
            ) {
                BottomBar(articleDetail)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            if (articleDetail.images.isNotEmpty()) {
                item {
                    Carousel(articleDetail.images)
                }
            }

            item {
                Text(text = articleDetail.title, fontSize = 30.sp)
            }
            item {
                Author(articleDetail)
            }
            item {
                Row {
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(
                                colorResource(id = R.color.grey)
                            )
                    ){
                        Text(text = articleDetail.type,fontSize = 10.sp, modifier = Modifier.padding(5.dp))
                    }
                }
            }
            item {
                Text(text = articleDetail.content, modifier = Modifier.padding(10.dp))
            }
            item {
                Comment(comments,articleDetail.comments)
            }
        }
    }
}

@Composable
fun BottomBar(articleDetail: ArticleDetail?) {
    var text by remember { mutableStateOf("") }
    var likeCount by remember { mutableIntStateOf(0) }
    var favoriteCount by remember { mutableIntStateOf(0) }
    var commentCount by remember { mutableIntStateOf(0) }
    if (articleDetail != null){
        likeCount = articleDetail.likes
        favoriteCount = articleDetail.favours
        commentCount = articleDetail.comments
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .background(Color.Gray.copy(alpha = 0.1f), shape = MaterialTheme.shapes.small)
                .padding(8.dp)
        )

        IconButton(onClick = { likeCount++ /*todo viewmodel*/}) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like")
                Text(text = likeCount.toString(), fontSize = 10.sp)
            }
        }

        IconButton(onClick = { favoriteCount++ /*todo viewmodel*/}) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Favorite")
                Text(text = favoriteCount.toString(), fontSize = 10.sp)
            }
        }


        IconButton(onClick = { commentCount++ /*todo viewmodel*/}) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.MailOutline, contentDescription = "Comment")
                Text(text = commentCount.toString(), fontSize = 10.sp)
            }
        }
    }
}




@Composable
internal fun Author(articleDetail: ArticleDetail) {

    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //头像
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = articleDetail.avatar)
                    .apply {
                        crossfade(true)
                        placeholder(R.drawable.ic_avatar)
                        error(R.drawable.ic_avatar)
                    }.build()
            ),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)// Set the size to 50.dp to make it square
                .padding(horizontal = 5.dp)
        )

        Column {
            Text(text = articleDetail.user_name)
            Text(text = timeConvertor(articleDetail.post_time), color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { articleDetail.is_focus = if (articleDetail.is_focus == 0) 1 else 0 },
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            if (articleDetail.is_focus == 0) Text(text = "+关注", fontSize = 10.sp, color = Color.White)
            else Text(text = "已关注", fontSize = 10.sp, color = Color.White)
        }
    }

}

@Composable
fun Comment(comments:List<Comment>,number: Int) {
    ScrollableTabRow(
        backgroundColor = Color.White,
        selectedTabIndex = 0,
        edgePadding = 0.dp // 移除边缘填充使标签对齐到开始位置
    ) {
        Tab(
            selected = true,
            onClick = { },
            text = { Text("评论$number") }

        )
    }
    comments.forEach{
        RowItem(it)
    }
}

@Composable
internal fun RowItem(comment: Comment) {
    val titleFontSize = 18.sp
    val timeFontSize = 12.sp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(comment.avatar),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 8.dp)
                .height(titleFontSize.value.dp + timeFontSize.value.dp)
        )
        Column {
            Text(
                text = comment.username,
                fontSize = titleFontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.heightIn(min = titleFontSize.value.dp)
            )
            Text(
                text = timeConvertor(comment.post_time),
                color = Color.Gray,
                fontSize = timeFontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.heightIn(min = timeFontSize.value.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = comment.content)
        }
    }
}
