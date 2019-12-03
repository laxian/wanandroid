package com.laxian.wanandroid.model.repository

import com.laxian.wanandroid.model.api.HttpClient
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.ArticleList

class HomeRepository : IRepository {
    suspend fun getHomeArticles(page: Int): Result<ArticleList> {
        return successOrThrow(
            HttpClient.service.getHomeArticles(page)
                .await()
        )
    }
}