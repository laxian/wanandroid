package com.laxian.wanandroid.model.repository

import com.laxian.wanandroid.model.User
import com.laxian.wanandroid.model.api.HttpClient
import com.laxian.wanandroid.model.bean.WanResponse
import com.laxian.wanandroid.model.api.Result
import java.io.IOException

class LoginRepository {
    suspend fun login(username: String, password: String): Result<*> {
        return safeCall(call = {
            Result.Success(
                HttpClient.service.login2(username, password)
                    .await()
            )
        })
    }

    private suspend fun safeCall(call: suspend () -> Result.Success<WanResponse<User>>): Result<*> {
        return try {
            call()
        } catch (e: Exception) {
            Result.Error<java.lang.Exception>(e)
        }
    }

    private fun execCall(response: WanResponse<User>): Result.Success<WanResponse<User>> =
        Result.Success(response)
}