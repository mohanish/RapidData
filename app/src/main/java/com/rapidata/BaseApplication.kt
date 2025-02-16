package com.rapidata

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.rapidata.box.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                netModule,
                apiModule,
                databaseModule
            ))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}