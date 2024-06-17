package cn.chiichen.gamevibes.service

import cn.chiichen.gamevibes.model.response.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiService {
    @GET("/post/list/hot")
    fun getHotList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<ArticleResponse>


    @GET("/post/list/recommend")
    fun getRecommendList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<ArticleResponse>

}