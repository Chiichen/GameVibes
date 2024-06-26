package cn.chiichen.gamevibes.service

import cn.chiichen.gamevibes.model.response.BaseResponse
import cn.chiichen.gamevibes.model.response.UserProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApiService {
    @GET("/api/user/user_info")
    fun getUserProfile(
        @Header("Authorization") auth: String
    ): Call<BaseResponse<UserProfileResponse>>
}