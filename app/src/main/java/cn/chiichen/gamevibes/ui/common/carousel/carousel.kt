package cn.chiichen.gamevibes.ui.common.carousel


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult

@Composable
fun Carousel( images: List<String>) {
    var aspectRatio by remember { mutableFloatStateOf(0f) }
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )
    val context = LocalContext.current

    LaunchedEffect(images) {
        if (images.isNotEmpty()) {
            val imageRequest = ImageRequest.Builder(context)
                .data(images[0])
                .build()

            val result = coil.ImageLoader(context).execute(imageRequest)
            if (result is SuccessResult) {
                val drawable = result.drawable
                val width = drawable.intrinsicWidth.toFloat()
                val height = drawable.intrinsicHeight.toFloat()
                val ratio = width / height
                aspectRatio = if (ratio > 16f / 9f) 16f / 9f
                else if (ratio < 3f / 4f) 3f / 4f
                else ratio
            }
        }
    }
    if (aspectRatio != 0f) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(aspectRatio)
            ) { page ->
                Image(
                    painter = rememberAsyncImagePainter(images[page]),
                    contentDescription = "Page: $page",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(pagerState.pageCount) { pageIndex ->
                    val isSelected = pagerState.currentPage == pageIndex
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = if (isSelected) Color.White else Color.Gray,
                        modifier = Modifier
                            .width(if (isSelected) 24.dp else 12.dp)
                            .height(8.dp)
                            .padding(horizontal = 2.dp)
                    ) {}
                }
            }
        }
    }
}