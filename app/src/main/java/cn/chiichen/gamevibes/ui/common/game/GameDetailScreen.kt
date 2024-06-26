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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
fun GameDetailScreen(navController: NavHostController, id: String, viewModel: GameDetailViewModel = viewModel()) {
    val images by viewModel.images.collectAsState()

    Column {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
                        Text(text = viewModel.title.value)
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
                GameDescription(viewModel.content.value)
            }
            item{
                GameReview(navController, viewModel)
            }
        }
    }
}

@Composable
fun GameDescription(content: String) {
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
                text = content,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
@Composable
fun GameReview(navController: NavHostController,viewModel: GameDetailViewModel) {
    val rating = viewModel.rating.doubleValue
    val reviews by viewModel.reviews.collectAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column{
            Column(modifier = Modifier.padding(15.dp).fillMaxWidth()){
                Text(text = "玩家点评")
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = rememberAsyncImagePainter(model = viewModel.avatar),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "发布你的点评")
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.clickable(onClick = {
                           navController.navigate("gameReview/${viewModel.title.value}")
                        })
                    ){
                        Image(
                            painter = painterResource(
                                id = if (rating >= 2.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(
                                id = if (rating >= 4.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(
                                id = if (rating >= 6.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(
                                id = if (rating >= 8.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(
                                id = if (rating >= 10.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            Divider(color = Color.Gray, thickness = 1.dp)
           reviews.forEach {
                RowItem(it)
            }
        }
    }
}

@Composable
private fun RowItem(review: Review) {
    val rating = review.rating
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier.height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = rememberAsyncImagePainter(model = review.avatar),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = review.name)
                Text(text = review.time)
            }
        }
        Row {
            Image(
                painter = painterResource(
                    id = if (rating >= 2.0) R.drawable.ic_star_click
                    else R.drawable.ic_star
                ),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Image(
                painter = painterResource(
                    id = if (rating >= 4.0) R.drawable.ic_star_click
                    else R.drawable.ic_star
                ),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Image(
                painter = painterResource(
                    id = if (rating >= 6.0) R.drawable.ic_star_click
                    else R.drawable.ic_star
                ),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Image(
                painter = painterResource(
                    id = if (rating >= 8.0) R.drawable.ic_star_click
                    else R.drawable.ic_star
                ),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Image(
                painter = painterResource(
                    id = if (rating >= 10.0) R.drawable.ic_star_click
                    else R.drawable.ic_star
                ),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }
        Text(text = review.comment)
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev (){
    val navController = rememberNavController()
    GameDetailScreen(navController, "10")
}
