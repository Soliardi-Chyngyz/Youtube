package com.chyngyz.youtube

import android.app.Application
import com.chyngyz.youtube.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}