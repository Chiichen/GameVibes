package cn.chiichen.gamevibes.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.chiichen.gamevibes.R


@Composable
fun GuideScreen(navController: NavController){
    val innerNavController = rememberNavController()
    NavHost(navController = innerNavController, startDestination = "first") {
        composable("first") { FirstPage(navController = innerNavController, outerNavController = navController) }
        composable("second") { SecondPage(navController = innerNavController, outerNavController = navController) }
        composable("third") { ThirdPage(outerNavController = navController) }
    }
}

@Composable
fun FirstPage(navController: NavController, outerNavController: NavController){
    Box(modifier = Modifier.fillMaxWidth()){
        Text(
            text = "跳过",
            fontSize = 20.sp,
            color = colorResource(id = R.color.yellow_100),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(40.dp)
                .clickable(onClick = {
                    outerNavController.navigate("home")
                }
                ),
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(180.dp))
        Image(
            painter = painterResource(id = R.drawable.im_guide_first),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "加入我们，构建你的游戏社区。",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(100.dp))
        IconButton(onClick = {
            navController.navigate("second")
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                modifier = Modifier.align(Alignment.CenterHorizontally).size(100.dp),
                contentDescription = ""
            )
        }
    }
}


@Composable
fun SecondPage(navController: NavController, outerNavController: NavController){
    Box(modifier = Modifier.fillMaxWidth()){
        Text(
            text = "跳过",
            fontSize = 20.sp,
            color = colorResource(id = R.color.yellow_100),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(40.dp)
                .clickable(onClick = {
                    outerNavController.navigate("home")
                }
                ),
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(180.dp))
        Image(
            painter = painterResource(id = R.drawable.im_guide_sec),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "在这里，每个玩家的声音都能被听到。",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(100.dp))
        IconButton(onClick = {
            navController.navigate("third")
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                modifier = Modifier.align(Alignment.CenterHorizontally).size(100.dp),
                contentDescription = ""
            )
        }
    }
}


@Composable
fun ThirdPage(outerNavController: NavController){
    Box(modifier = Modifier.fillMaxWidth()){
        Text(
            text = "跳过",
            fontSize = 20.sp,
            color = colorResource(id = R.color.yellow_100),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(40.dp)
                .clickable(onClick = {
                    outerNavController.navigate("home")
                }
                ),
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(180.dp))
        Image(
            painter = painterResource(id = R.drawable.im_guide_third),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "记录你的游戏旅程，分享你的游戏故事。",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(120.dp))
        Button(
            onClick = {
                outerNavController.navigate("home")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.yellow_100)
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .width(250.dp)
                .height(70.dp)
        ) {
            Text(text = "开始旅程", color = Color.White, fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun pp(){
    val navController = rememberNavController()
    GuideScreen(navController = navController)
}