package com.laxian.wanandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laxian.wanandroid.model.User
import com.laxian.wanandroid.model.api.HttpClient
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.WanResponse
import com.laxian.wanandroid.model.repository.LoginRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(val repository: LoginRepository) : ViewModel() {

    private val _uiState = MutableLiveData<User>().apply {
    }
    val uiState: LiveData<User> = _uiState

    fun login(username: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = repository.login(username, password)
            if (response is Result.Success) {
                _uiState.value = (response.data as WanResponse<User>).data
            }
        }
    }
}