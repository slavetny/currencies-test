package com.slavetny.test.data.network

import com.slavetny.test.domain.model.Convert
import com.slavetny.test.domain.model.Currencies
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesService {

    @GET("latest")
    suspend fun getCurrencies(): Currencies

    @GET("convert")
    suspend fun getConvertedValue(@Query("from") currencyFrom: String,
                                  @Query("to") currencyTo: String,
                                  @Query("amount") amount: Int): Convert

}