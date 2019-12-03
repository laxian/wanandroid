package com.laxian.wanandroid.model.repository

import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.WanResponse
import com.laxian.wanandroid.model.bean.err

interface IRepository

suspend fun <T : Any> safeCall(call: suspend () -> Result.Success<T>): Result<T> =
    try {
        call()
    } catch (e: Exception) {
        throw e
    }

// errorCode == 0 代表请求成功，否则，请求错误
fun <T : WanResponse<*>> successOrThrow(response: T): Result.Success<T> {
    val errorCode = response.errorCode
    if (errorCode != 0) throw response.err()
    return Result.Success(response)
}