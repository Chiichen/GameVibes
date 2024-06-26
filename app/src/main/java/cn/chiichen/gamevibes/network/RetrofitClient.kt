package cn.chiichen.gamevibes.network


import cn.chiichen.gamevibes.service.ArticleApiService
import cn.chiichen.gamevibes.service.AuthApiService
import cn.chiichen.gamevibes.service.GameApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://8.138.154.250:8080"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articleApiService: ArticleApiService by lazy {
        instance.create(ArticleApiService::class.java)
    }

    val gameApiService:GameApiService by lazy{
        instance.create(GameApiService::class.java)
    }

    private const val AUTH_BASE_URL = "https://auth.chiichen.cn/"

    private val authInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApiService: AuthApiService by lazy {
        authInstance.create(AuthApiService::class.java);
    }
}