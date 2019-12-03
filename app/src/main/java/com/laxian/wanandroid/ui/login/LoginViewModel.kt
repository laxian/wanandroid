package com.laxian.wanandroid.ui.login

import androidx.lifecycle.MutableLiveData
import com.laxian.ktx.base.BaseViewModel
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.User
import com.laxian.wanandroid.model.repository.LoginRepository

class LoginViewModel(val repository: LoginRepository) : BaseViewModel() {

    val userData = MutableLiveData<User>()

    fun login(username: String, password: String) {
        launchOnUITryCatch({
            val result = repository.login(username, password)
            if (result is Result.Success) {
                userData.value = result.data
            }
        }, true)
    }
}