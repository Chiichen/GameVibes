package cn.chiichen.gamevibes.ui.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.game.UserRepository
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val userRepository = UserRepository()
    val isLoggedIn = mutableStateOf(false)
    val username = mutableStateOf("一个不重要的ID")
    val following = mutableStateOf(12)
    val followers = mutableStateOf(0)
    val likes = mutableStateOf(685)
    val joinDate = mutableStateOf("加入gamevibe 1天")
    val location = mutableStateOf("IP: 广东")
    val id = mutableStateOf("ID: 641344569")
    val bio = mutableStateOf("专属于你的gamevibe！")

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val mmkv = MMKV.defaultMMKV()
        val token = mmkv.decodeString("login_token")
        isLoggedIn.value = false
        if (token != null) {
            fetchUserData(token)
        }
    }

    private fun fetchUserData(token: String) {
        viewModelScope.launch {
            val userData = userRepository.getUserProfile(token)
            userData?.let { data ->
                username.value = data.nickName
                following.value = data.focusCount
                followers.value = data.fansCount
                likes.value = data.likeCollectCount
                joinDate.value = data.createTime
                location.value = data.ipAddr
                id.value = "ID:" + data.id.toString()
                bio.value = data.intro
                isLoggedIn.value = true
            }
        }
    }
}