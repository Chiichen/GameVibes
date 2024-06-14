package cn.chiichen.gamevibes.ui.common.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.model.entities.loadMoreArticles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecommendViewModel: ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private var currentIndex = 0

    init {
        loadMore()
    }

    fun loadMore() {
        viewModelScope.launch {
            val newArticles = loadMoreArticles(currentIndex, 10)
            _articles.value = _articles.value + newArticles
            currentIndex += newArticles.size
        }
    }
}