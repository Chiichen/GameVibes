package cn.chiichen.gamevibes.ui.common.article

import androidx.lifecycle.ViewModel

data class AuthorInfo(
    val avatar:String,
    val name:String,
    val time:String,
    val isFollow:Boolean
)

data class ArticleDetail(
    val images:List<String>,
    val title: String,
    val authorInfo: AuthorInfo,
    val tags:List<String>,
    val content: String,
    val likes:Int = 0,
    val favorites: Int = 0,
    val comments:Int = 0
)

data class Comment(
    val avatar:String,
    val name:String,
    val time:String,
    val content: String
)

class ArticleDetailViewModel:ViewModel() {
    //todo
}