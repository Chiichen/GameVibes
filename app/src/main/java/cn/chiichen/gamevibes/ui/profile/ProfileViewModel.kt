package cn.chiichen.gamevibes.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
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
        if (token != null) {
            isLoggedIn = true
            fetchUserData(token)
        } else {
            isLoggedIn = false
        }
    }

    private fun fetchUserData(token: String) {
        viewModelScope.launch {
            // TODO: Replace with actual API call to fetch user data
            // For example:
            // val userData = apiService.getUserData(token)
            // Update state with user data
        }
    }
}