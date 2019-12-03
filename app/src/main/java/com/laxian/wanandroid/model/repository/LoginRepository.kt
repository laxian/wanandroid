package com.laxian.wanandroid.model.repository

import com.laxian.wanandroid.model.User
import com.laxian.wanandroid.model.api.HttpClient
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.WanResponse

class LoginRepository : IRepository {
    suspend fun login(username: String, password: String): Result<WanResponse<User>> {
        return successOrThrow(
            HttpClient.service.login2(username, password)
                .await()
        )
    }
}