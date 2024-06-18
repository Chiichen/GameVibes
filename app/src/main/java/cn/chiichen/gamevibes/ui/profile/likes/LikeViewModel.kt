package cn.chiichen.gamevibes.ui.profile.likes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class LikeViewModel : ViewModel() {
    var content by mutableStateOf("什么也没有")
}