package com.magistor8.mvp

import android.app.Application
import android.content.Context
import com.magistor8.mvp.data.RepoImpl
import com.magistor8.mvp.data.retrofit.RemoteDataSource

class App : Application() {

    val repo by lazy {
        RepoImpl(RemoteDataSource())
    }

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

val Context.app: App
    get() = applicationContext as App