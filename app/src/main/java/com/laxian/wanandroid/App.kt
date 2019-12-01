package com.laxian.wanandroid

import android.app.Application
import android.content.Context
import com.laxian.wanandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}