package cn.chiichen.gamevibes.model.request

data class SearchRequest(
    val current: Int,
    val pageSize: Int,
    val searchText: String = "",
    val sortField: String = "",
    val sortOrder: String = ""
)