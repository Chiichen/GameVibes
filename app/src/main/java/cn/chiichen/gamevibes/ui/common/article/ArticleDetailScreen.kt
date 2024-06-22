package cn.chiichen.gamevibes.ui.common.article

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.ui.common.carousel.Carousel


@Composable
fun ArticleDetailScreen(navController: NavHostController) {
    //测试数据
    val images: List<Int> = listOf(
        R.drawable.image4,
        R.drawable.image3,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "正文")
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { }) { }
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
                elevation = 8.dp
            ) {
                BottomBar()
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            if (images.isNotEmpty()) {
                item {
                    Carousel(images)
                }
            }
            item {
                Text(text = "title", fontSize = 50.sp)
            }
            item {
                Author()
            }
            item {
                Row {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "tags", fontSize = 10.sp)
                    }
                }
            }
            item {
                Text(text = "这几天心里颇不宁静。今晚在院子里坐着乘凉，忽然想起日日走过的荷塘，在这满月的光里，总该另有一番样子吧。\n")
            }
            item {
                Comment()
            }
        }
    }
}

@Composable
fun BottomBar() {
    var text by remember { mutableStateOf("") }
    var likeCount by remember { mutableStateOf(0) }
    var favoriteCount by remember { mutableStateOf(0) }
    var commentCount by remember { mutableStateOf(0) }

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

        IconButton(onClick = { likeCount++ }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like")
                Text(text = likeCount.toString(), fontSize = 10.sp)
            }
        }

        IconButton(onClick = { favoriteCount++ }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Favorite")
                Text(text = favoriteCount.toString(), fontSize = 10.sp)
            }
        }


        IconButton(onClick = { commentCount++ }) {
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
internal fun Author() {
    //TODO 修改样式 和 加载
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //头像
        Image(
            painter = painterResource(id = R.drawable.ic_avatar),
            contentDescription = "",
            modifier = Modifier.fillMaxHeight(1f))
        Column {
            Text(text = "Author")
            Text(text = "time", color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
            Text(text = "+关注")
        }
    }
}

@Composable
fun Comment() {
    val number = 10
    ScrollableTabRow(
        selectedTabIndex = 0,
        edgePadding = 0.dp // 移除边缘填充使标签对齐到开始位置
    ) {
        Tab(
            selected = true,
            onClick = { },
            text = { Text("评论$number") }

        )
    }
    //todo 添加循环展示rowItem()
    RowItem()
    RowItem()
    RowItem()
}

@Composable
internal fun RowItem() {
    val titleFontSize = 20.sp
    val timeFontSize = 15.sp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_avatar),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 8.dp)
                .height(titleFontSize.value.dp + timeFontSize.value.dp)
        )
        Column {
            Text(
                text = "name",
                fontSize = titleFontSize,
                modifier = Modifier.height(titleFontSize.value.dp)
            )
            Text(
                text = "time",
                color = Color.Gray,
                fontSize = timeFontSize,
                modifier = Modifier.height(timeFontSize.value.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "comment")
        }
    }
}

fun getAspectRatioForImage(context: Context, imageRes: Int): Float {
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeResource(context.resources, imageRes, options)
    val ratio = options.outWidth.toFloat() / options.outHeight.toFloat()
    return if(ratio > 16f/9f) 16f/9f
    else if(ratio < 3f/4f) 3f/4f
    else ratio
}

@Composable
@Preview(showBackground = true)
fun ADPrev(){
    val navController = rememberNavController()
    ArticleDetailScreen(navController)
}