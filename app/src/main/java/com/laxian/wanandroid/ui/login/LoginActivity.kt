package com.laxian.wanandroid.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.laxian.ktx.ext.jump
import com.laxian.wanandroid.R
import com.laxian.wanandroid.model.repository.LoginRepository
import com.laxian.wanandroid.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bt_login.setOnClickListener(this)
//        val loginViewModelFactory = LoginViewModelFactory(LoginRepository())
//        loginViewModel =
//            ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
        startObserve()
    }

    private fun startObserve() {
        loginViewModel.apply {
            uiState.observe(this@LoginActivity, Observer {
                toHome()
            })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_login -> onLoginPressed()
        }
    }

    private fun onLoginPressed() {
        loginViewModel.login(et_username.text.toString(), et_password.text.toString())
    }

    private fun toHome() = jump(MainActivity::class.java)
}

class LoginViewModelFactory(val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getDeclaredConstructor(LoginRepository::class.java)
            .newInstance(repository)
    }
}