package com.laxian.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.laxian.ktx.base.BaseViewModel
import com.laxian.wanandroid.model.api.Result
import com.laxian.wanandroid.model.bean.ArticleList
import com.laxian.wanandroid.model.repository.HomeRepository

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val _text = MutableLiveData<ArticleList>()
    val text: LiveData<ArticleList> = _text

    fun getHomeArticles(page: Int) {
        launchOnUITryCatch({
            val result = repository.getHomeArticles(page)
            if (result is Result.Success) {
                _text.value = result.data
            }
        }, true)
    }
}