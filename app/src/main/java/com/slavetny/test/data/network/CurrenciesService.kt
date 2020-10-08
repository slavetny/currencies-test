package com.slavetny.test.data.network

import com.slavetny.test.domain.model.Currencies
import retrofit2.http.GET

interface CurrenciesService {

    @GET("latest")
    suspend fun getCurrencies() : Currencies

}