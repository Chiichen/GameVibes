package cn.chiichen.gamevibes.repository

import cn.chiichen.gamevibes.model.entities.AuthToken
import cn.chiichen.gamevibes.model.request.TokenDetailRequest
import cn.chiichen.gamevibes.network.RetrofitClient.authApiService
import com.tencent.mmkv.MMKV
import retrofit2.awaitResponse
import java.time.Instant

class UserRepository {
    private val mmkv: MMKV = MMKV.defaultMMKV()

    /// 验证token是否有效
    /// @return  True为有效，False为无效是否有效
    suspend fun getToken(): AuthToken? {
        val token = mmkv.decodeString("login_token") ?: return null;
        val tokenDetailRequest = TokenDetailRequest(code = token);
        val response =
            authApiService.getTokenDetail(tokenDetailRequest).awaitResponse().body() ?: return null;
        val authToken = AuthToken(
            response.access_token,
            response.id_token,
            response.refresh_token,
            response.token_type, Instant.now().plusSeconds(response.expires_in.toLong()),
            response.scope
        );
        return authToken;
    }
}