package cn.chiichen.gamevibes.model.entities


data class Article(
    val id: Int,
    val title: String,
    val pv: Int,
    val postTime: String,
    val comments: Int,
    val imageRes: String,
    val type: String
)