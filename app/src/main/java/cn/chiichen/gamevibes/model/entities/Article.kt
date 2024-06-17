package cn.chiichen.gamevibes.model.entities

import android.util.Log
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class Article(
    val id: Int,
    val title: String,
    val pv: Int,
    val postTime: String,
    val comments: Int,
    val imageRes: String,
    val type: String
)


//fun loadMoreArticles(startIndex: Int, count: Int): List<Article> {
//    return List(count) { index -> Article(startIndex + index, "Article Title ${startIndex + index}") }
//}