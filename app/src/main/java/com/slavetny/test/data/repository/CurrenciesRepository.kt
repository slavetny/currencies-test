package com.slavetny.test.data.repository

import com.slavetny.test.data.db.CurrenciesEntity
import com.slavetny.test.data.network.Result
import com.slavetny.test.domain.model.Convert
import com.slavetny.test.domain.model.Currencies

interface CurrenciesRepository {
    suspend fun getCurrencies(): Result<Currencies>
    suspend fun getConvertedCurrency(currencyFrom: String, currencyTo: String, amount: Int): Result<Convert>
    suspend fun addCurrencyInFavorite(currency: CurrenciesEntity)
    suspend fun getAllCurrencies(): List<String>
    suspend fun deleteCurrency(currency: CurrenciesEntity)
}