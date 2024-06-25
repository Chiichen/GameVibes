package cn.chiichen.gamevibes.model.response

//{
//    "access_token": "eyJhb...",
//    "id_token": "eyJhb...",
//    "refresh_token": "eyJhb...",
//    "token_type": "Bearer",
//    "expires_in": 10080,
//    "scope": "openid"
//}
data class TokenDetailResponse(
    var access_token: String,
    var id_token: String,
    var refresh_token: String,
    var token_type: String,
    var expires_in: Int,
    var scope: String,
)