package cn.chiichen.gamevibes.ui.messages.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CommentMessage(
    val id: String,
    val user: String,
    val content: String,
    val timestamp: String,
    val imageUrl: String,
    val isRead: Boolean
)

class CommentViewModel : ViewModel() {
    private val _comments = MutableStateFlow<List<CommentMessage>>(emptyList())
    val comments: StateFlow<List<CommentMessage>> = _comments

    init {
        fetchComments()
    }

    private fun fetchComments() {
        viewModelScope.launch {
            // 模拟从后台接口获取数据
            _comments.value = listOf(
                CommentMessage(
                    "1",
                    "一个ID",
                    "回复了我的帖子",
                    "1分钟前",
                    "https://example.com/image1.png",
                    false
                ),
                CommentMessage(
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