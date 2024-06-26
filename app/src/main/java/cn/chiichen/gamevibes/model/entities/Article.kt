package cn.chiichen.gamevibes.model.entities


data class Article(
    val id: Long,
    val title: String,
    val type: String,
    val image: String,
    val comments: Int,
    val pv: Int,
    val post_time: String,
)