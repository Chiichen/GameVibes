package cn.chiichen.gamevibes.model.entities

import java.time.Instant


data class AuthToken(
    var accessToken: String,
    var idToken: String,
    var refreshToken: String,
    var tokenType: String,
    var expired: Instant,
    var scope: String,
)