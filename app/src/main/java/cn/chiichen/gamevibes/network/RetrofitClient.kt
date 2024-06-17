package cn.chiichen.gamevibes.network


import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.service.ArticleApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.example.com/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articleApiService: ArticleApiService by lazy {
        instance.create(ArticleApiService::class.java)
    }
}