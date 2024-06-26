package cn.chiichen.gamevibes.model.response

import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.ui.common.article.Comment

data class CommentResponse(
    val records: MutableList<Comment>,
    val total: Int
)
