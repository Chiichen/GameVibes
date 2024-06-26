package cn.chiichen.gamevibes.ui.home.hot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.model.response.BaseResponse
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

    private var currentPage = 1

    init {
        loadMore()
    }

    fun loadMore() {
        viewModelScope.launch {
            val call = RetrofitClient.articleApiService.getHotList(pageRequest = PageRequest(currentPage, 10))
            call.enqueue(object : Callback<BaseResponse<ArticleResponse>> {
                override fun onResponse(call: Call<BaseResponse<ArticleResponse>>, response: Response<BaseResponse<ArticleResponse>>) {
                    if (response.isSuccessful) {
                        val articleResponse = response.body()
                        if (articleResponse != null) {
                            val updatedArticles = _articles.value.toMutableList()
                            updatedArticles.addAll(articleResponse.data.records)
                            _articles.value = updatedArticles
                            currentPage += 1
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<ArticleResponse>>, t: Throwable) {
                    // 处理错误，比如记录日志或者显示提示
                }
            })
        }
    }

}