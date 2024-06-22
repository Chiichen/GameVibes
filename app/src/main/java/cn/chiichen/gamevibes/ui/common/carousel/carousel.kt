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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cn.chiichen.gamevibes.ui.common.article.getAspectRatioForImage

@Composable
fun Carousel( images: List<Int>) {

    val context = LocalContext.current
    var aspectRatio = 0f
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )
    if(images.isNotEmpty()) {
        aspectRatio = remember { getAspectRatioForImage(context, images[0]) }
    }
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
                painter = painterResource(id = images[page]),
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