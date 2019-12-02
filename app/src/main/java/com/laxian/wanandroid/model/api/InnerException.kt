package com.laxian.wanandroid.model.api

import com.laxian.wanandroid.model.bean.WanResponse
import java.lang.Exception


class InnerException private constructor(
    val errorCode: Int,
    errorMsg: String
) : Exception(errorMsg) {

    companion object {
        fun of(response: WanResponse<*>): InnerException {
            return InnerException(response.errorCode, response.errorMsg)
        }
    }

    override fun toString(): String {
        return "InnerException@[errorCode: $errorCode, errorMsg: $message]"
    }
}
