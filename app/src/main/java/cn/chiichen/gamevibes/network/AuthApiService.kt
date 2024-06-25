package cn.chiichen.gamevibes.service

import cn.chiichen.gamevibes.model.request.TokenDetailRequest
import cn.chiichen.gamevibes.model.response.TokenDetailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    //{
    //    "access_token": "eyJhb...",
    //    "id_token": "eyJhb...",
    //    "refresh_token": "eyJhb...",
    //    "token_type": "Bearer",
    //    "expires_in": 10080,
    //    "scope": "openid"
    //}
    @POST("/api/login/oauth/access_token")
    fun getTokenDetail(
        @Body request: TokenDetailRequest
    ): Call<TokenDetailResponse>
}