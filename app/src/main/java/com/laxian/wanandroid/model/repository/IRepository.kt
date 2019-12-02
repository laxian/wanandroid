package com.laxian.wanandroid.model.repository

import com.laxian.wanandroid.model.User
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.WanResponse

interface IRepository

suspend fun safeCall(call: suspend () -> Result.Success<WanResponse<User>>): Result<*> =
    try {
        call()
    } catch (e: Exception) {
        Result.Error<java.lang.Exception>(e)
    }

