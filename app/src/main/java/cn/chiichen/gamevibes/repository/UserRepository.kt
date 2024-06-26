package cn.chiichen.game


import cn.chiichen.gamevibes.model.entities.UserProfile
import cn.chiichen.gamevibes.network.RetrofitClient.userApiService
import com.tencent.mmkv.MMKV
import retrofit2.awaitResponse

class UserRepository {
    private val mmkv: MMKV = MMKV.defaultMMKV()

    suspend fun getUserProfile(token: String): UserProfile? {
        val response = userApiService.getUserProfile(token).awaitResponse()
        response.body()?.let { body ->
            if (body.code == 200) {
                return UserProfile(
                    body.data.avatar,
                    body.data.createTime,
                    body.data.fansCount,
                    body.data.focusCount,
                    body.data.id,
                    body.data.ipAddr,
                    body.data.intro,
                    body.data.likeCollectCount,
                    body.data.nickName,
                    body.data.userId,
                )
            } else {
                return UserProfile(
                    body.data.avatar,
                    body.data.createTime,
                    body.data.fansCount,
                    body.data.focusCount,
                    body.data.id,
                    body.data.ipAddr,
                    body.data.intro,
                    body.data.likeCollectCount,
                    body.data.nickName,
                    body.data.userId,
                )
//                return null
            }

        }
        return null
    }
}