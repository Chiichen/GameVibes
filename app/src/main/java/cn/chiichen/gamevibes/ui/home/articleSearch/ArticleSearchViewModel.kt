package cn.chiichen.gamevibes.ui.home.articleSearch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    private var currentPage = 0

    fun updateSearchText(newText: String) {
        _searchText.value = newText
    }

    fun addSearchHistory(newText: String){
        _searchHistory.value += newText
    }
    fun getSearchFinding(){
        viewModelScope.launch {
            // 测试数据 todo delete
            _searchFindings.value = listOf(
                "每日商店",
                "悟空",
                "gta6",
                "双人成行",
                "黑神话悟空豪华版",
                "pubg",
                "双人成行双人成行",
                "无畏契约",
            )
        }
    }

    fun getHotTopics() {
        viewModelScope.launch {
            // 测试数据 todo delete
            _hotTopics.value = listOf(
                "黑神话悟空",
                "文明6",
                "艾尔登法环",
                "艾尔登法环黄金树幽影",
                "中国式家长",
                "怪物猎人世界",
                "极限竞速地平线5",
                "绝地求生",
                "荒野大镖客救赎2",
                "我为情狂"
            )
        }
    }

    fun getArticles(searchText: String, order: Int = 0){
        viewModelScope.launch {
            currentPage = 0
            // 测试数据 todo delete
            _articles.value = listOf(
                Article(
                    1, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    2, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    3, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    4, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    5, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                )
            )
        }
    }

    fun getMoreArticles(){
        viewModelScope.launch {
            // 测试数据 todo delete
            _articles.value += listOf(
                Article(
                    1, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    2, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    3, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    4, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                ),
                Article(
                    5, "title", 100,
                    "2024-06-02T14:15:22Z", 10,
                    "https://img0.baidu.com/it/u=350592823,3182430235&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800",
                    "测试类型"
                )
            )
        }
    }
}