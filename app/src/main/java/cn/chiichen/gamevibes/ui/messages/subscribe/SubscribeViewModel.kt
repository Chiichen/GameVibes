package cn.chiichen.gamevibes.ui.messages.subscribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SubscribeMessage(
    val id: Long,
    val user: String,
    val content: String,
    val timestamp: String,
    val imageUrl: String,
    val isRead: Boolean
)

class SubscribeViewModel : ViewModel() {
    private val _subscribes = MutableStateFlow<List<SubscribeMessage>>(emptyList())
    val subscribes: StateFlow<List<SubscribeMessage>> = _subscribes

    init {
        fetchSubscribe()
    }

    private fun fetchSubscribe() {
        viewModelScope.launch {
            // 模拟从后台接口获取数据
            _subscribes.value = listOf(
                SubscribeMessage(
                    1,
                    "一个ID",
                    "关注了我",
                    "1分钟前",
                    "https://example.com/image1.png",
                    false
                ),
                SubscribeMessage(
                    2,
                    "玩家67165008",
                    "关注了我",
                    "1分钟前",
                    "https://example.com/image2.png",
                    true
                )
            )
        }
    }
}