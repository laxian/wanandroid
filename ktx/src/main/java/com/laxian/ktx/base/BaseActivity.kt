package com.laxian.ktx.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laxian.ktx.ext.hideKeyboard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        hideKeyboard()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}