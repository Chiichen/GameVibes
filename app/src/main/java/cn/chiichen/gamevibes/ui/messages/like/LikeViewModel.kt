package cn.chiichen.gamevibes.ui.messages.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LikeMessage(
    val id: String,
    val user: String,
    val content: String,
    val timestamp: String,
    val imageUrl: String,
    val isRead: Boolean
)

class LikeViewModel : ViewModel() {
    private val _likes = MutableStateFlow<List<LikeMessage>>(emptyList())
    val likes: StateFlow<List<LikeMessage>> = _likes

    init {
        fetchLikes()
    }

    private fun fetchLikes() {
        viewModelScope.launch {
            // 模拟从后台接口获取数据
            _likes.value = listOf(
                LikeMessage(
                    "1",
                    "一个ID",
                    "回复了我的帖子",
                    "1分钟前",
                    "https://example.com/image1.png",
                    false
                ),
                LikeMessage(
                    "2",
                    "玩家67165008",
                    "测试一下帖子",
                    "1分钟前",
                    "https://example.com/image2.png",
                    true
                )
            )
        }
    }
}