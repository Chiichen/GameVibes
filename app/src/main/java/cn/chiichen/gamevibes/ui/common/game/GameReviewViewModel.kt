package cn.chiichen.gamevibes.ui.common.game

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameReviewViewModel:ViewModel() {
    private var _rating = mutableDoubleStateOf(0.0)
    val rating = _rating

    private var _content = mutableStateOf("")
    val content = _content

    fun postGameReview() {
        viewModelScope.launch {
            /*TODO*/
        }
    }

    fun updateRating(rating:Double){
        viewModelScope.launch {
            _rating.doubleValue = rating
        }
    }

    fun updateContent(content: String) {
        viewModelScope.launch {
            _content.value = content
        }
    }
}