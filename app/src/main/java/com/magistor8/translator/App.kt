package com.magistor8.translator

import android.app.Application
import com.magistor8.translator.di.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin{
            androidContext(this@App)
            modules(myModule)
        }
    }

}