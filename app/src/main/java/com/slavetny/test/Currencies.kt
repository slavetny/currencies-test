package com.slavetny.test

import android.app.Application
import com.slavetny.test.di.databaseModule
import com.slavetny.test.di.networkModule
import com.slavetny.test.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Currencies : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Currencies)
            androidLogger()
            modules(
                listOf(
                    viewModelModule, networkModule, databaseModule
                )
            )
        }
    }
}