package com.laxian.wanandroid.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laxian.wanandroid.model.User
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.WanResponse
import com.laxian.wanandroid.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(val repository: LoginRepository) : ViewModel() {

    val userData = MutableLiveData<User>()
    val errorMsg = MutableLiveData<String>()

    fun login(username: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = repository.login(username, password)
            if (result is Result.Success) {
                userData.value = (result.data as WanResponse<User>).data
            } else {
                errorMsg.value = (result as Result.Error<*>).exception.message
            }
        }
    }
}