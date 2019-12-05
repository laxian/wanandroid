package com.laxian.wanandroid.model.repository

import com.laxian.wanandroid.model.api.HttpClient
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.ArticleList
import com.laxian.wanandroid.model.bean.Banner

class HomeRepository : IRepository {
    suspend fun getHomeArticles(page: Int): Result<ArticleList> {
        return successOrThrow(
            HttpClient.service.getHomeArticles(page)
                .await()
        )
    }

    suspend fun getBanner(): Result<List<Banner>> {
        return successOrThrow(
            HttpClient.service.getBanner()
                .await()
        )
    }
}