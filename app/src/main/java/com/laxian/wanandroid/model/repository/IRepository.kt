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

// errorCode == 0 代表请求成功，否则，请求错误，抛出异常
// 如果需要在ViewModel里自定义处理errorCode，不要使用此方法
fun <Response : WanResponse<Data>, Data : Any> successOrThrow(response: Response): Result.Success<Data> {
    val errorCode = response.errorCode
    if (errorCode != 0) throw response.err()
    return Result.Success(response.data)
}