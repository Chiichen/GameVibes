package cn.chiichen.gamevibes.repository

import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.network.RetrofitClient.articleApiService
class ArticleRepository {
    suspend fun getHotList(pageRequest: PageRequest): Result<List<Article>> {
        return try {
            val response = articleApiService.getHotList(pageRequest).execute()
            if (response.isSuccessful) {
                val articleResponse = response.body()
                if (articleResponse != null) {
                    Result.success(articleResponse.data.records)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Response not successful"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getSearchFindings(count: Int): List<String>? {
        return try {
            val response = articleApiService.getSearchFinding(count).execute()
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}