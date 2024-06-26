package cn.chiichen.gamevibes.model.request

data class CommentsRequest(
    val current: Int,
    val pageSize: Int,
    val post_id: Long,
    val sortField: String = "",
    val sortOrder: String = ""
)
