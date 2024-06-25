package cn.chiichen.gamevibes.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://8.138.154.250"

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