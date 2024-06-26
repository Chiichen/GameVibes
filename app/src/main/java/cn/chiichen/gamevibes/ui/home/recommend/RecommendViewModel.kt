package cn.chiichen.gamevibes.ui.home.recommend

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.model.response.BaseResponse
import cn.chiichen.gamevibes.network.RetrofitClient
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendViewModel: ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news = _news

    private var currentPage = 1

    init {
        getNews()
        loadMore()
    }

    private fun getNews(){
        viewModelScope.launch {
            val call = RetrofitClient.articleApiService.getNews(pageRequest = PageRequest(1, 5))
            call.enqueue(object : Callback<BaseResponse<ArticleResponse>> {
                override fun onResponse(call: Call<BaseResponse<ArticleResponse>>, response: Response<BaseResponse<ArticleResponse>>) {
                    if (response.isSuccessful) {
                        val articleResponse = response.body()
                        if (articleResponse != null) {
                            _news.value += articleResponse.data.records
                            currentPage += 1
                        } else {
                            Log.e("RecommendViewModel", "ArticleResponse is null")
                        }
                    } else {
                        Log.e("RecommendViewModel", "Response is not successful: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<BaseResponse<ArticleResponse>>, t: Throwable) {
                    Log.e("RecommendViewModel", "API call failed", t)
                }
            })
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            val call = RetrofitClient.articleApiService.getRecommendList(pageRequest = PageRequest(currentPage, 10))
            call.enqueue(object : Callback<BaseResponse<ArticleResponse>> {
                override fun onResponse(call: Call<BaseResponse<ArticleResponse>>, response: Response<BaseResponse<ArticleResponse>>) {
                    if (response.isSuccessful) {
                        val articleResponse = response.body()
                        if (articleResponse != null) {
                            val updatedArticles = _articles.value.toMutableList()
                            updatedArticles.addAll(articleResponse.data.records)
                            _articles.value = updatedArticles
                            currentPage += 1
                        } else {
                            Log.e("RecommendViewModel", "ArticleResponse is null")
                        }
                    } else {
                        Log.e("RecommendViewModel", "Response is not successful: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<BaseResponse<ArticleResponse>>, t: Throwable) {
                    Log.e("RecommendViewModel", "API call failed", t)
                }
            })
        }
    }
}