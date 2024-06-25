package cn.chiichen.gamevibes.ui.common.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R

@Composable
fun GameReviewScreen(navController: NavController, name: String?,viewModel: GameReviewViewModel = viewModel()){
    var rating by remember { mutableDoubleStateOf(viewModel.rating.doubleValue) }
    var content by remember {
        mutableStateOf(viewModel.content.value)
    }
    Column(
        modifier = Modifier.background(colorResource(id = R.color.grey))
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
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
                Text(text = "点评")
            }
            IconButton(onClick = {}) {}
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(
                topStart = CornerSize(60.dp),
                topEnd = CornerSize(60.dp),
                bottomEnd = CornerSize(0.dp),
                bottomStart = CornerSize(0.dp)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        rating = 2.0
                        viewModel.updateRating(rating)
                    }) {
                        Image(
                            painter = painterResource(
                                id = if (rating >= 2.0) R.drawable.ic_star_click
                                    else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {
                        rating = 4.0
                        viewModel.updateRating(rating)
                    }) {
                        Image(
                            painter = painterResource(
                                id = if (rating >= 4.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {
                        rating = 6.0
                        viewModel.updateRating(rating)
                    }) {
                        Image(
                            painter = painterResource(
                                id = if (rating >= 6.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {
                        rating = 8.0
                        viewModel.updateRating(rating)
                    }) {
                        Image(
                            painter = painterResource(
                                id = if (rating >= 8.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {
                        rating = 10.0
                        viewModel.updateRating(rating)
                    }) {
                        Image(
                            painter = painterResource(
                                id = if (rating >= 10.0) R.drawable.ic_star_click
                                else R.drawable.ic_star
                            ),
                            contentDescription = ""
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .background(Color.Gray),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name!!,
                        modifier = Modifier
                            .padding(horizontal = 20.dp , vertical = 5.dp)
                    )
                }
                TextField(
                    value = content,
                    onValueChange = {
                        content = it
                        viewModel.updateContent(content)
                    },
                    placeholder = { Text(text = "评价内容...")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
                Button(
                    onClick = {
                        viewModel.postGameReview()
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(horizontal = 30.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "发布")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev (){
    val navController = rememberNavController()
    GameReviewScreen(navController, "name")

}
