package com.laxian.wanandroid.model.repository

import com.laxian.wanandroid.model.User
import com.laxian.wanandroid.model.api.HttpClient
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.WanResponse
import com.laxian.wanandroid.model.bean.err

class LoginRepository : IRepository {
    suspend fun login(username: String, password: String): Result<*> {
        return safeCall(call = {
            successOrThrow(
                HttpClient.service.login2(username, password)
                    .await()
            )
        })
    }

    private fun successOrThrow(response: WanResponse<User>): Result.Success<WanResponse<User>> {
        val errorCode = response.errorCode
        if (errorCode != 0) throw response.err()
        return Result.Success(response)
    }
}