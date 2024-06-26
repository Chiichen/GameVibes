package cn.chiichen.gamevibes.ui.messages.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SaveMessage(
    val id: Long,
    val user: String,
    val content: String,
    val timestamp: String,
    val imageUrl: String,
    val isRead: Boolean
)

class SaveViewModel : ViewModel() {


    private val _saves = MutableStateFlow<List<SaveMessage>>(emptyList())
    val saves: StateFlow<List<SaveMessage>> = _saves

    init {
        fetchSave()
    }

    private fun fetchSave() {
        viewModelScope.launch {
            // 模拟从后台接口获取数据
            _saves.value = listOf(
                SaveMessage(
                    1,
                    "一个ID",
                    "收藏了我的帖子",
                    "1分钟前",
                    "https://example.com/image1.png",
                    false
                ),
                SaveMessage(
                    2,
                    "玩家67165008",
                    "收藏了我的帖子",
                    "1分钟前",
                    "https://example.com/image2.png",
                    true
                )
            )
        }
    }
}