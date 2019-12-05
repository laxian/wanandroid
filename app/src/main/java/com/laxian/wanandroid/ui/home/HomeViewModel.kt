package com.laxian.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.laxian.ktx.base.BaseViewModel
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.ArticleList
import com.laxian.wanandroid.model.bean.Banner
import com.laxian.wanandroid.model.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val _articles = MutableLiveData<ArticleList>()
    val articles: LiveData<ArticleList> = _articles
    private val _banner = MutableLiveData<List<Banner>>()
    val bannerData: LiveData<List<Banner>> = _banner

    fun getHomeArticles(page: Int) {
        launchOnUITryCatch({
            val result = repository.getHomeArticles(page)
            if (result is Result.Success) {
                _articles.value = result.data
            }
        }, true)
    }

    fun getBanner() {
        launchOnUITryCatch({
            val result = repository.getBanner()
            if (result is Result.Success) {
                _banner.value = result.data
            }
        }, true)
    }
}