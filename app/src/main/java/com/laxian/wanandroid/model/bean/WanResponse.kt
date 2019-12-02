package com.laxian.wanandroid.model.bean

import com.laxian.wanandroid.model.api.InnerException

data class WanResponse<T>(val errorCode: Int, val errorMsg: String, val data: T)

fun WanResponse<*>.err() = InnerException.of(this)