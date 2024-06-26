package cn.chiichen.gamevibes.service

import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.model.request.SearchRequest
import cn.chiichen.gamevibes.model.response.BaseResponse
import cn.chiichen.gamevibes.model.response.GameResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GameApiService {
    @POST("/api/game/list/page/vo")
    fun getGameList(
        @Body pageRequest: PageRequest
    ):Call<BaseResponse<GameResponse>>

    @POST("/api/game/search")
    fun getGameSearch(
        @Body searchRequest:SearchRequest
    ):Call<BaseResponse<GameResponse>>
}