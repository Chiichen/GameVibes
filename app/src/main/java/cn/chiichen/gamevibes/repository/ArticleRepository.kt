package cn.chiichen.gamevibes.repository

import cn.chiichen.gamevibes.model.dao.ArticleDao
import cn.chiichen.gamevibes.model.entities.Article
import cn.chiichen.gamevibes.service.ArticleApiService

class ArticleRepository(private val apiService: ArticleApiService, val articleDao: ArticleDao) {
}