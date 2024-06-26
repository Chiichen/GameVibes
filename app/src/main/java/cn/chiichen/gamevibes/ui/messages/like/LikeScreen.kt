package cn.chiichen.gamevibes.ui.messages.like

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun LikeScreen(navController: NavController, viewModel: LikeViewModel = LikeViewModel()) {
    val likes by viewModel.likes.collectAsState()
    LazyColumn {
        items(likes) { like ->
            LikeMessageItem(
                like,
                onMessageClick = {
                    navController.navigate("article/${like.id}")
                })
        }
    }
}

@Composable
fun LikeMessageItem(message: LikeMessage, onMessageClick: (LikeMessage) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onMessageClick(message) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = message.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = message.user)
            Text(text = message.content)
            Text(text = message.timestamp)
        }
        if (!message.isRead) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.Red)
                    .padding(start = 8.dp)
            )
        }
    }
}