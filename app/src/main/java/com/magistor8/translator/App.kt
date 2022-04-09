package com.magistor8.translator

import android.app.Application
import android.content.Context
import com.magistor8.translator.di.DaggerMyComponent
import com.magistor8.translator.di.MyModule

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    val di by lazy {
        DaggerMyComponent.builder()
            .myModule(MyModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}

val Context.app: App
    get() = applicationContext as App