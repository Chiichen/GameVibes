package cn.chiichen.gamevibes.model.entities

data class UserProfile(
    val avatar: String,
    val createTime: String,
    val fansCount: Int,
    val focusCount: Int,
    val id: Long,
    val intro: String,
    val ipAddr: String,
    val likeCollectCount: Int,
    val nickName: String,
    val userId: String
)