package cn.chiichen.gamevibes.ui.settings

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SettingViewModel : ViewModel() {
    var username by mutableStateOf("玩家11")
    var userId by mutableStateOf("ID: 641344569")
}