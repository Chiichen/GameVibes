package cn.chiichen.gamevibes.ui.common.article

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.request.CommentsRequest
import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.model.response.BaseResponse
import cn.chiichen.gamevibes.model.response.CommentResponse
import cn.chiichen.gamevibes.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException


data class ArticleDetail(
    var id: Long,
    var avatar: String,
    var user_name: String,
    var post_time: String,
    var is_focus: Int,
    var images: List<String>,
    var title: String,
    var type: String,
    var content: String,
    var likes: Int = 0,
    var favours: Int = 0,
    var comments: Int = 0,
    var is_favor: Int,
    var is_like: Int,
    var location: String,
    var pv: Int
)

data class Comment(
    var avatar:String,
    var username:String,
    var post_time: String,
    var content: String
)

class ArticleDetailViewModel(id: Long):ViewModel() {
    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    var comments = _comments

    private val _articleDetail = MutableStateFlow(
        ArticleDetail(
            id = 0L,
            avatar = "",
            user_name = "",
            post_time = "2024-06-17 23:41:13",
            is_focus = 0,
            images = emptyList(),
            title = "",
            type = "",
            content = "",
            is_favor = 0,
            is_like = 0,
            location = "",
            pv = 0
        )
    )
    val articleDetail = _articleDetail


    private val _id = id
    private var currentPage = 1

    init {
        getArticleDetail()
        getComments()
    }

    private fun getArticleDetail() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.articleApiService.getArticle(_id).awaitResponse()
                if (response.isSuccessful) {
                    val articleResponse = response.body()
                    if (articleResponse != null) {
                        _articleDetail.value = _articleDetail.value.copy(
                            id = articleResponse.data.id,
                            avatar = articleResponse.data.avatar,
                            user_name = articleResponse.data.user_name,
                            post_time = articleResponse.data.post_time,
                            is_focus = articleResponse.data.is_focus,
                            images = articleResponse.data.images,
                            title = articleResponse.data.title,
                            type = articleResponse.data.type,
                            content = articleResponse.data.content,
                            likes = articleResponse.data.likes,
                            favours = articleResponse.data.favours,
                            comments = articleResponse.data.comments,
                            is_favor = articleResponse.data.is_favor,
                            is_like = articleResponse.data.is_like,
                            location = articleResponse.data.location,
                            pv = articleResponse.data.pv
                        )
                    }
                } else {
                    // Handle the error response
                    Log.e("getArticleDetail", "Error response code: ${response.code()}")
                    // Optionally, update UI or state to reflect the error
                }
            } catch (e: IOException) {
                // Network or conversion error
                Log.e("getArticleDetail", "Network error: ${e.message}")
                // Optionally, update UI or state to reflect the network error
            } catch (e: HttpException) {
                // HTTP error response
                Log.e("getArticleDetail", "HTTP error: ${e.message()}")
                // Optionally, update UI or state to reflect the HTTP error
            } catch (e: Exception) {
                // Any other exceptions
                Log.e("getArticleDetail", "Unexpected error: ${e.message}")
                // Optionally, update UI or state to reflect the unexpected error
            }
        }
    }


    private fun getComments() {
        viewModelScope.launch {
            try {
                val call = RetrofitClient.articleApiService.getComments(CommentsRequest(currentPage, 10, _id))
                call.enqueue(object : Callback<BaseResponse<CommentResponse>> {
                    override fun onResponse(call: Call<BaseResponse<CommentResponse>>, response: Response<BaseResponse<CommentResponse>>) {
                        if (response.isSuccessful) {
                            val articleResponse = response.body()
                            if (articleResponse != null) {
                                val currentList = _comments.value.toMutableList()
                                currentList.addAll(articleResponse.data.records)
                                _comments.value = currentList.toList()
                                currentPage++
                            }
                        } else {
                            // Handle the error response
                            Log.e("getComments", "Error response code: ${response.code()}")
                            // Optionally, update UI or state to reflect the error
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<CommentResponse>>, t: Throwable) {
                        when (t) {
                            is IOException -> {
                                // Network or conversion error
                                Log.e("getComments", "Network error: ${t.message}")
                                // Optionally, update UI or state to reflect the network error
                            }
                            is HttpException -> {
                                // HTTP error response
                                Log.e("getComments", "HTTP error: ${t.message()}")
                                // Optionally, update UI or state to reflect the HTTP error
                            }
                            else -> {
                                // Any other exceptions
                                Log.e("getComments", "Unexpected error: ${t.message}")
                                // Optionally, update UI or state to reflect the unexpected error
                            }
                        }
                    }
                })
            } catch (e: Exception) {
                // Handle any exceptions that might occur during the enqueue process
                Log.e("getComments", "Unexpected error in launch scope: ${e.message}")
                // Optionally, update UI or state to reflect the unexpected error
            }
        }
    }

}