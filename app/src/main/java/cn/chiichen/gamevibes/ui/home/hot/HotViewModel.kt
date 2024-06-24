package cn.chiichen.gamevibes.ui.home.hot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Article
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
            // 测试数据 todo delete
            _articles.value += listOf(
                Article(1,"title",100,
                    "2024-06-02T14:15:22Z",10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"),
                Article(2,"title",100,
                    "2024-06-02T14:15:22Z",10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"),
                Article(3,"title",100,
                    "2024-06-02T14:15:22Z",10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"),
                Article(4,"title",100,
                    "2024-06-02T14:15:22Z",10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"),
                Article(5,"title",100,
                    "2024-06-02T14:15:22Z",10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型")
            )

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