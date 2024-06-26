package cn.chiichen.gamevibes.ui.home.articleSearch

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.model.request.SearchRequest
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.model.response.BaseResponse
import cn.chiichen.gamevibes.network.RetrofitClient
import cn.chiichen.gamevibes.network.RetrofitClient.articleApiService
import cn.chiichen.gamevibes.repository.ArticleRepository
import cn.chiichen.gamevibes.service.ArticleApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class ArticleSearchViewModel :ViewModel() {
    //搜索框输入
    private var _searchText =  mutableStateOf("")
    val searchText = _searchText

    //搜索历史
    private var _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory = _searchHistory

    //搜索发现
    private var _searchFindings = MutableStateFlow<List<String>>(emptyList())
    val searchFindings = _searchFindings

    //热门讨论
    private var _hotTopics = MutableStateFlow<List<String>>(emptyList())
    val hotTopics = _hotTopics

    //搜索结果
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private var currentPage = 1

    fun updateSearchText(newText: String) {
        _searchText.value = newText
    }

    fun addSearchHistory(newText: String){
        _searchHistory.value += newText
    }
    fun getSearchFinding() {
        viewModelScope.launch {
            try {
                val call = RetrofitClient.articleApiService.getSearchFinding(10)
                call.enqueue(object : Callback<BaseResponse<List<String>>> {
                    override fun onResponse(call: Call<BaseResponse<List<String>>>, response: Response<BaseResponse<List<String>>>) {
                        if (response.isSuccessful) {
                            try {
                                // 尝试解析响应体
                                val responseBody = response.body()
                                if (responseBody != null && responseBody.code == 0) {
                                    _searchFindings.value = responseBody.data
                                } else {
                                    _searchFindings.value = emptyList()
                                }
                            } catch (e: Exception) {
                                Log.e("getSearchFinding", "Parsing error", e)
                            }
                        } else {
                            // 打印原始响应字符串
                            val errorBody = response.errorBody()?.string()
                            Log.e("getSearchFinding", "Error response: $errorBody")
                            _searchFindings.value = emptyList()
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<List<String>>>, t: Throwable) {
                        _searchFindings.value = emptyList()
                        Log.e("getSearchFinding", "Request failed", t)
                    }
                })
            } catch (e: Exception) {
                _searchFindings.value = emptyList()
                Log.e("getSearchFinding", "Exception caught", e)
            }
        }
    }

    fun getHotTopics() {
        viewModelScope.launch {
            try {
                val call = articleApiService.getHotTopics(PageRequest(1,10))
                call.enqueue(object : Callback<BaseResponse<List<String>>> {
                    override fun onResponse(call: Call<BaseResponse<List<String>>>, response: Response<BaseResponse<List<String>>>) {
                        if (response.isSuccessful) {
                            try {
                                // 尝试解析响应体
                                val responseBody = response.body()
                                if (responseBody != null && responseBody.code == 0) {
                                    _hotTopics.value = responseBody.data
                                } else {
                                    _hotTopics.value = emptyList()
                                }
                            } catch (e: Exception) {
                                Log.e("getHotTopics", "Parsing error", e)
                            }
                        } else {
                            // 打印原始响应字符串
                            val errorBody = response.errorBody()?.string()
                            Log.e("getHotTopics", "Error response: $errorBody")
                            _hotTopics.value = emptyList()
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<List<String>>>, t: Throwable) {
                        _hotTopics.value = emptyList()
                        Log.e("getHotTopics", "Request failed", t)
                    }
                })
            }catch(e: Exception) {
                Log.e("getHotTopics","Exception caught",e)
            }
        }
    }

    fun getArticles(order: Int){
        viewModelScope.launch {
            val sort = if(order == 0) "pv" else "post_time"
            val call = articleApiService.getArticles(SearchRequest(currentPage,10,_searchText.value,sort,"desc"))
            call.enqueue(object : Callback<BaseResponse<ArticleResponse>> {
                override fun onResponse(call: Call<BaseResponse<ArticleResponse>>, response: Response<BaseResponse<ArticleResponse>>) {
                    if (response.isSuccessful) {
                        val articleResponse = response.body()
                        if (articleResponse != null) {
                            _articles.value += articleResponse.data.records
                            currentPage += 1
                        } else {
                            Log.e("getArticles", "ArticleResponse is null")
                        }
                    } else {
                        Log.e("getArticles", "Response is not successful: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<BaseResponse<ArticleResponse>>, t: Throwable) {
                    Log.e("getArticles", "API call failed", t)
                }
            })
        }
    }

    fun clean() {
        viewModelScope.launch {
            _articles.value = emptyList()
        }
    }
}