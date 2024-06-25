package cn.chiichen.gamevibes.model.request

import cn.chiichen.gamevibes.config.GameVibesConfig


//{
//    "grant_type": "authorization_code",
//    "client_id": ClientId,
//    "client_secret": ClientSecret,
//    "code": Code,
//}
data class TokenDetailRequest(
    val grant_type: String = "authorization_code",
    val client_id: String = GameVibesConfig.casdoor_clientID,
    val client_secret: String = GameVibesConfig.casdoor_secret,
    val code: String
)
