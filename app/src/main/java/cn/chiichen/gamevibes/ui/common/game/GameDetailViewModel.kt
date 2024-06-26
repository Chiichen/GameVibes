package cn.chiichen.gamevibes.ui.common.game

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class Review(
    val avatar: String,
    val name:String,
    val time:String,
    val rating: Double,
    val comment:String
)

class GameDetailViewModel: ViewModel() {
    private val _title = mutableStateOf("")
    val title = _title


    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images = _images

    private val _content = mutableStateOf("")
    val content = _content


    private val _avatar = mutableStateOf("")
    val avatar = _avatar

    private val _isRated = mutableStateOf(false)
    val isRated = _isRated

    private val _rating = mutableDoubleStateOf(0.0)
    val rating = _rating

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews = _reviews

    var currentPage = 0

    init {
        loadGameDetail()
        loadReviews()
    }

    private fun loadReviews() {
        //TODO("Not yet implemented")
        viewModelScope.launch {
            //测试数据
            _title.value = "无畏契约"
            _images.value = listOf(
                "https://img2.baidu.com/it/u=3875359732,2062969706&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
                "https://img1.baidu.com/it/u=3800337747,3674258435&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
                "https://img0.baidu.com/it/u=1761351807,973569589&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
                "https://olimg.3dmgame.com/uploads/images/gamenews/20200429/1588123930_751798.jpg"
            )
            _content.value = "《无畏契约》,又名VALORANT、瓦罗兰特，是《英雄联盟》开发商拳头游戏开发、腾讯代理的PC端5V5英雄战术射击网游（PC）,自上线后吸引了全球亿万FPS爱好者的喜爱。" +
                    "\n在游戏中，玩家将化身为能力各异的英雄，使用不同类型的枪械与对手展开战斗。" +
                    "\n游戏注重射击技巧与创造性的玩法，拥有极强的竞技性与战术策略性，节奏激烈、爽快感强，还有丰富多样的游戏模式。"
            _avatar.value = "https://img1.baidu.com/it/u=1774028090,1192351745&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            _isRated.value = false
            _rating.doubleValue = 0.0
            _reviews.value = listOf(
                Review(
                    avatar = "https://img1.baidu.com/it/u=1774028090,1192351745&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
                    name = "name",
                    time = "000",
                    rating = 4.0,
                    comment = "你说得对，但是",
                )
            )
        }
    }

    fun loadGameDetail(){
        //todo
    }
}