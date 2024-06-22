package cn.chiichen.gamevibes.ui.common.game

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R
import cn.chiichen.gamevibes.ui.common.carousel.Carousel
import cn.chiichen.gamevibes.ui.games.GamesScreen

@Composable
fun GameDetailScreen(){

    //测试数据
    val images: List<Int> = listOf(
        R.drawable.image4,
        R.drawable.image3,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
    )

    Column {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "",
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Name")
                    }
                    IconButton(onClick = {}) {}
                }
            },
            backgroundColor = colorResource(id = R.color.grey)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Carousel(images = images)
            }
            item{
                GameDescription()
            }
            item{
                GameReview()
            }
        }
    }
}

@Composable
fun GameDescription() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "游戏简介",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "《浮岛物语》+双人联机+全球史上最低价格=《浮岛物语》国服手机版，今天终于正式发售了！...",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
@Composable
fun GameReview() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column{
            Column(modifier = Modifier.padding(15.dp)){
                Text(text = "玩家点评")
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_avatar),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "发布你的点评")
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.clickable(onClick = {/* TODO */})
                    ){
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                        Icon(imageVector = Icons.Default.Star, contentDescription = "")
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            Divider(color = Color.Gray, thickness = 1.dp)
            RowItem()
            RowItem()
            RowItem()
            RowItem()
            RowItem()
            RowItem()
            RowItem()
        }
    }
}

@Composable
private fun RowItem(){
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier.height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_avatar),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "id")
                Text(text = "time")
            }
        }
        Row {
            Icon(imageVector = Icons.Default.Star, contentDescription = "")
            Icon(imageVector = Icons.Default.Star, contentDescription = "")
            Icon(imageVector = Icons.Default.Star, contentDescription = "")
            Icon(imageVector = Icons.Default.Star, contentDescription = "")
            Icon(imageVector = Icons.Default.Star, contentDescription = "")
        }
        Text(text = "comment")
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev (){
    val navController = rememberNavController()
    GameDetailScreen()
}
