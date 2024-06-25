package cn.chiichen.gamevibes.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.game.UserRepository
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val userRepository = UserRepository()
    var isLoggedIn by mutableStateOf(false)
    var username by mutableStateOf("一个不重要的ID")
    var following by mutableStateOf(12)
    var followers by mutableStateOf(0)
    var likes by mutableStateOf(685)
    var joinDate by mutableStateOf("加入gamevibe 1天")
    var location by mutableStateOf("IP: 广东")
    var id by mutableStateOf("ID: 641344569")
    var bio by mutableStateOf("专属于你的gamevibe！")

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val mmkv = MMKV.defaultMMKV()
        val token = mmkv.decodeString("login_token")
        isLoggedIn = false
        if (token != null) {
            fetchUserData(token)
        }
    }

    private fun fetchUserData(token: String) {
        viewModelScope.launch {
            val userData = userRepository.getUserProfile(token)
            userData?.let { data ->
                username = data.nickName
                following = data.focusCount
                followers = data.fansCount
                likes = data.likeCollectCount
                joinDate = data.createTime
                location = data.ipAddr
                id = "ID:" + data.id.toString()
                bio = data.intro
                isLoggedIn = true
            }
        }
    }
}