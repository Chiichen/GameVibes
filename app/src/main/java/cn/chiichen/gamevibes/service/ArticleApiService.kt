package cn.chiichen.gamevibes.service

import cn.chiichen.gamevibes.model.request.CommentsRequest
import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.model.request.SearchRequest
import cn.chiichen.gamevibes.model.response.ArticleResponse
import cn.chiichen.gamevibes.model.response.BaseResponse
import cn.chiichen.gamevibes.model.response.CommentResponse
import cn.chiichen.gamevibes.ui.common.article.ArticleDetail
import cn.chiichen.gamevibes.ui.common.article.Comment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ArticleApiService {

    @POST("/post/list/hot")
    fun getHotList(
        @Body pageRequest: PageRequest
    ): Call<BaseResponse<ArticleResponse>>


    @POST("/post/list/recommend")
    fun getRecommendList(
        @Body pageRequest: PageRequest
    ): Call<BaseResponse<ArticleResponse>>

    @POST("/news/list/page")
    fun getNews(
        @Body pageRequest: PageRequest
    ): Call<BaseResponse<ArticleResponse>>


    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("/api/game/names")
    fun getSearchFinding(
        @Query("count") count:Int
    ):Call<BaseResponse<List<String>>>

    @POST("/post/list/title")
    fun getHotTopics(
        @Body pageRequest: PageRequest
    ):Call<BaseResponse<List<String>>>

    @POST("/post/search")
    fun getArticles(
        @Body searchRequest: SearchRequest
    ): Call<BaseResponse<ArticleResponse>>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("/post/get")
    fun getArticle(
        @Query("id") id: Long
    ):Call<BaseResponse<ArticleDetail>>

    @POST("/post_comment/list")
    fun getComments(
        @Body commentsRequest: CommentsRequest
    ):Call<BaseResponse<CommentResponse>>
}