package com.laxian.wanandroid.model.api

import java.lang.Exception

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error<T: Any>(val exception: Exception) : Result<Exception>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success: [$data]"
            is Error<*> -> "Error: [$exception]"
        }
    }
}