package com.laxian.wanandroid.ui.home

import androidx.lifecycle.Observer
import com.laxian.ktx.base.BaseVMFragment
import com.laxian.ktx.ext.toast
import com.laxian.wanandroid.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseVMFragment<HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {

    }

    override fun initData() {
        homeViewModel.getHomeArticles(10000)
    }

    override fun startObserve() {
        super.startObserve()
        homeViewModel.apply {
            text.observe(this@HomeFragment, Observer {
                text_home.text = it.toString()
            })
        }
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        context?.let { toast(it, e.message ?: e.toString()) }
    }

    override fun getVM(): HomeViewModel? = homeViewModel
}
