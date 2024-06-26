package cn.chiichen.gamevibes.model.response

import cn.chiichen.gamevibes.model.entities.Article

data class ArticleResponse(
    var records: MutableList<Article>,
    val total: Int
)
