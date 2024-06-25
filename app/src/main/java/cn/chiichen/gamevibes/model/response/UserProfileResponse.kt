package cn.chiichen.gamevibes.model.response

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("create_time") val createTime: String,
    @SerializedName("fans_count") val fansCount: Int,
    @SerializedName("focus_count") val focusCount: Int,
    @SerializedName("id") val id: Long,
    @SerializedName("intro") val intro: String,
    @SerializedName("ip_addr") val ipAddr: String,
    @SerializedName("like_collect_count") val likeCollectCount: Int,
    @SerializedName("nick_name") val nickName: String,
    @SerializedName("user_id") val userId: String
)