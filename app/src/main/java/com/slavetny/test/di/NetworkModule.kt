package com.slavetny.test.di

import com.slavetny.test.data.network.CurrenciesService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideCurrenciesService(get()) }
}

fun provideDefaultOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.exchangerate.host/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideCurrenciesService(retrofit: Retrofit):
        CurrenciesService = retrofit.create(CurrenciesService::class.java)