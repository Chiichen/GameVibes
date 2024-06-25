package cn.chiichen.gamevibes.repository

import cn.chiichen.gamevibes.network.ArticleApiService
import cn.chiichen.gamevibes.network.RetrofitClient

class ArticleRepository {
    suspend fun getHotPosts(page: Int, pageSize: Int) =
        RetrofitClient.articleApiService.getHotPosts(page, pageSize)
}