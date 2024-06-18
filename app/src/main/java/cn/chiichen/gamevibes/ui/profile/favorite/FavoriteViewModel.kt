package cn.chiichen.gamevibes.ui.profile.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FavoriteViewModel : ViewModel() {
    var content by mutableStateOf("什么也没有")
}
