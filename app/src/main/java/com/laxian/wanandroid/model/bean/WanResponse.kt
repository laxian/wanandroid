package com.laxian.wanandroid.model.bean

data class WanResponse<T>(val code: Int, val msg: String, val data:T)
