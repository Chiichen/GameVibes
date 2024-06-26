package cn.chiichen.gamevibes.model.request

data class PageRequest(
    val current: Int,
    val pageSize: Int,
    val sortField: String = "",
    val sortOrder: String = ""
)