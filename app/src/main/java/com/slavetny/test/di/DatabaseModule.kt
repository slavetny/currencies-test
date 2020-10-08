package com.slavetny.test.di

import android.app.Application
import androidx.room.Room
import com.slavetny.test.data.db.CurrenciesDao
import com.slavetny.test.data.db.CurrenciesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideCurrenciesDao(get()) }
}

fun provideDatabase(application: Application) =
    Room.databaseBuilder(application.applicationContext,
        CurrenciesDatabase::class.java, "currencies.db")
        .build()


fun provideCurrenciesDao(database: CurrenciesDatabase): CurrenciesDao {
    return database.currenciesDao()
}