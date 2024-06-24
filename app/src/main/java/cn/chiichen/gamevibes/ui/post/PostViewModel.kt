package cn.chiichen.gamevibes.ui.post

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.ui.games.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel(){

    private var _images = MutableStateFlow<List<Uri>>(emptyList())

    private var _searchText = mutableStateOf("")
    val searchText = _searchText

    private var _title = mutableStateOf("")
    val title = _title

    private var _content = mutableStateOf("")
    val content = _content

    private var _relatedGame = mutableStateOf("")
    val relatedGame = _relatedGame

    private var _relatedGames = MutableStateFlow<List<Game>>(emptyList())
    val relatedGames = _relatedGames

    init {
        updateImages(emptyList())
        updateTitle("")
        updateContent("")
    }
    fun updateImages(images: List<Uri>){
        viewModelScope.launch {
            _images.value = images
        }
    }

    fun updateTitle(newText: String){
        viewModelScope.launch {
            _title.value = newText
        }
    }

    fun updateContent(newText: String){
        viewModelScope.launch {
            _content.value = newText
        }
    }



    fun post() {
        //TODO("Not yet implemented")
    }

    fun loadRelatedGames() {
//        TODO("Not yet implemented")
    }

    fun updateSearchText(searchText: String) {
        viewModelScope.launch {
            _searchText.value = searchText
        }
    }
}