package cn.chiichen.gamevibes.ui.profile.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    var content by mutableStateOf("什么也没有")
}
