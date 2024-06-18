package cn.chiichen.gamevibes.ui.profile

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ProfileViewModel : ViewModel() {
    var username by mutableStateOf("一个不重要的ID")
    var following by mutableStateOf(12)
    var followers by mutableStateOf(0)
    var likes by mutableStateOf(685)
    var joinDate by mutableStateOf("加入gamevibe 1天")
    var location by mutableStateOf("IP: 广东")
    var id by mutableStateOf("ID: 641344569")
    var bio by mutableStateOf("专属于你的gamevibe！")
}