package cn.chiichen.gamevibes.model.entities

import android.util.Log
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Article(
    val id: Long,
    val title: String,
    val type: String,
    val image: String,
    val comments: Int,
    val pv: Int,
    val post_time: String,
)