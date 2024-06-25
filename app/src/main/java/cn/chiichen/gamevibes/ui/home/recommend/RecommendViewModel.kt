package cn.chiichen.gamevibes.ui.home.recommend

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

class RecommendViewModel: ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news = _news

    private var currentPage = 0

    init {
        getNews()
        loadMore()
    }

    private fun getNews(){
        // 测试数据 todo delete
        _news.value = listOf(
            Article(id = 1, title = "title", comments = 100,
                postTime = "2024-06-02T14:15:22Z",pv = 10,
                image = "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                type = "测试类型"),
            Article(id = 2, title = "title", comments = 100,
                postTime = "2024-06-02T14:15:22Z",pv = 10,
                image = "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                type = "测试类型"),
            Article(id = 3, title = "title", comments = 100,
                postTime = "2024-06-02T14:15:22Z",pv = 10,
                image = "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                type = "测试类型"),
        )
    }

    fun loadMore() {
        viewModelScope.launch {
            // 测试数据 todo delete
            _articles.value += listOf(
                Article(id = 1, title = "title", comments = 100,
                    postTime = "2024-06-02T14:15:22Z",pv = 10,
                    image = "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    type = "测试类型"),
                Article(id = 2, title = "title", comments = 100,
                    postTime = "2024-06-02T14:15:22Z",pv = 10,
                    image = "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    type = "测试类型"),
                Article(id = 3, title = "title", comments = 100,
                    postTime = "2024-06-02T14:15:22Z",pv = 10,
                    image = "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    type = "测试类型"),
            )
//            val call = RetrofitClient.articleApiService.getRecommendList(currentPage, 10)
//            call.enqueue(object : Callback<ArticleResponse> {
//                override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
//                    if (response.isSuccessful) {
//                        val articleResponse = response.body()
//                        if (articleResponse != null) {
//                            _articles.value += articleResponse.articles
//                            currentPage += 1
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
//                    //todo
//                }
//            })
        }
    }
}