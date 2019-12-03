package com.laxian.wanandroid.di

import com.laxian.wanandroid.model.repository.HomeRepository
import com.laxian.wanandroid.model.repository.LoginRepository
import com.laxian.wanandroid.ui.home.HomeViewModel
import com.laxian.wanandroid.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository() }
    single { HomeRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)
