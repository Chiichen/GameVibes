package cn.chiichen.gamevibes.ui.common.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.ui.common.carousel.Carousel
import coil.compose.rememberAsyncImagePainter


@Composable
fun ArticleDetailScreen(navController: NavHostController, id: String, viewModel: ArticleDetailViewModel = viewModel()) {
    //测试数据
    val articleDetail = ArticleDetail(
        images = listOf(
            "https://dimg08.c-ctrip.com/images/fd/tg/g2/M00/D4/1C/Cghzf1VDK02Ad48xACTS77Yxmh0444.jpg",
            "https://pic4.zhimg.com/v2-c00c4fd3c4d3bf42c5f0af829debc6e2_r.jpg?source=1940ef5c",
            "https://img0.baidu.com/it/u=896214020,274705695&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800",
            "https://img1.baidu.com/it/u=2780218922,817541529&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=1422",
            "https://img1.baidu.com/it/u=3785324817,3055338308&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800"
        ),
        title = "An Interesting Article",
        authorInfo = AuthorInfo(
            avatar = "https://www.keaitupian.cn/cjpic/frombd/1/253/2232745444/1513375911.jpg",
            name = "John Doe",
            time = "2024-06-23",
            isFollow = false
        ),
        tags = listOf("Kotlin", "Programming", "Android"),
        content = "This is the content of the article. \nIt talks about various topics related to Kotlin programming and Android development.",
        likes = 120,
        favorites = 45,
        comments = 10
    )
    //测试
    val comments = listOf(
        Comment(
            avatar = "https://www.keaitupian.cn/cjpic/frombd/1/253/2232745444/1513375911.jpg",
            name = "Jane Doe",
            time = "2024-06-23 10:00",
            content = "This is a comment content. Great article!"
        ),
        Comment(
            avatar = "https://www.keaitupian.cn/cjpic/frombd/1/253/2232745444/1513375911.jpg",
            name = "Jane Doe",
            time = "2024-06-23 10:00",
            content = "This is a comment content. Great article!"
        ),
        Comment(
            avatar = "https://www.keaitupian.cn/cjpic/frombd/1/253/2232745444/1513375911.jpg",
            name = "Jane Doe",
            time = "2024-06-23 10:00",
            content = "This is a comment content. Great article!"
        ),
    )


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
                Author(articleDetail.authorInfo)
            }
            item {
                Row {
                    articleDetail.tags.forEach{
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .background(
                                    colorResource(id = R.color.grey)
                                )
                        ){
                            Text(text = it,fontSize = 10.sp, modifier = Modifier.padding(5.dp))
                        }
                    }
                }
            }
            item {
                Text(text = articleDetail.content, modifier = Modifier.padding(10.dp))
            }
            item {
                Comment(comments)
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
        favoriteCount = articleDetail.favorites
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
internal fun Author(authorInfo: AuthorInfo) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(vertical = 5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //头像
        Image(
            painter = rememberAsyncImagePainter(authorInfo.avatar),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 5.dp)
        )
        Column {
            Text(text = authorInfo.name)
            Text(text = authorInfo.time, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
        ) {
            if(!authorInfo.isFollow) Text(text = "+关注", fontSize = 10.sp)
            else Text(text = "已关注" , fontSize = 10.sp)
        }
    }
}

@Composable
fun Comment(comments:List<Comment>) {
    val number = 10
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
    val titleFontSize = 20.sp
    val timeFontSize = 15.sp

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
                text = comment.name,
                fontSize = titleFontSize,
                modifier = Modifier.height(titleFontSize.value.dp)
            )
            Text(
                text = comment.time,
                color = Color.Gray,
                fontSize = timeFontSize,
                modifier = Modifier.height(timeFontSize.value.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = comment.content)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ADPrev(){
    val navController = rememberNavController()
    ArticleDetailScreen(navController, "0")
}