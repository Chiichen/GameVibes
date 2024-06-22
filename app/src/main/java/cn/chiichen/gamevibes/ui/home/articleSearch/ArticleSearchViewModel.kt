package cn.chiichen.gamevibes.ui.home.articleSearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ArticleSearchViewModel :ViewModel() {
    private var _searchText =  mutableStateOf("")
    val searchText = _searchText
    //todo
}