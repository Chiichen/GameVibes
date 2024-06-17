package cn.chiichen.gamevibes.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.model.entities.loadMoreArticles
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotViewModel :ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private var currentPage = 0

    init {
        loadMore()
    }

    fun loadMore() {
        viewModelScope.launch {
            val call = RetrofitClient.articleApiService.getHotList(currentPage, 10)
            call.enqueue(object : Callback<ArticleResponse> {
                override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                    if (response.isSuccessful) {
                        val articleResponse = response.body()
                        if (articleResponse != null) {
                            _articles.value += articleResponse.articles
                            currentPage += 1
                        }
                    }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {

                }
            })
        }
    }

}