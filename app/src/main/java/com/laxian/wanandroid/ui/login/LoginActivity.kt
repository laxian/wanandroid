package com.laxian.wanandroid.ui.login

import android.app.ProgressDialog
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.laxian.ktx.base.BaseVMActivity
import com.laxian.ktx.ext.pushAndReplace
import com.laxian.ktx.ext.toast
import com.laxian.wanandroid.R
import com.laxian.wanandroid.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseVMActivity<LoginViewModel>(), View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initView() {
        bt_login.setOnClickListener(this)
        et_username.doOnTextChanged { s, _, _, _ ->
            textInputLayoutUsername.error = if (s.isNullOrEmpty()) "用户名不能为空" else null
        }
        et_password.doOnTextChanged { s, _, _, _ ->
            textInputLayoutPassword.error = if (s.isNullOrEmpty()) "密码不能为空" else null
        }
    }

    override fun getVM(): LoginViewModel? {
        return loginViewModel
    }

    override fun initData() {
        loginViewModel.apply {
            userData.observe(this@LoginActivity, Observer {
                dismissProgressDialog()
                toHome()
            })
            lifecycle::addObserver
        }
    }

    override fun onError(e: Throwable) {
        dismissProgressDialog()
        toast(e.message ?: e.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_login -> onLoginPressed()
        }
    }

    private fun onLoginPressed() {
        if (et_username.text.isNullOrEmpty()) {
            textInputLayoutUsername.error = "用户名不能为空"
        } else if (et_password.text.isNullOrEmpty()) {
            textInputLayoutPassword.error = "密码不能为空"
        } else {
            showProgressDialog()
            loginViewModel.login(et_username.text.toString(), et_password.text.toString())
        }
    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    private fun toHome() = pushAndReplace(MainActivity::class.java)
}