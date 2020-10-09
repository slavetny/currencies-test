package com.slavetny.test.data.repository

import com.slavetny.test.data.db.CurrenciesEntity
import com.slavetny.test.data.db.CurrenciesDao
import com.slavetny.test.data.network.CurrenciesService
import com.slavetny.test.data.network.Result

class CurrenciesRepositoryImpl(
    private val apiService: CurrenciesService,
    private val currenciesDao: CurrenciesDao
) : CurrenciesRepository {

    override suspend fun getCurrencies() =
        Result(apiService.getCurrencies())

    override suspend fun getConvertedCurrency(currencyFrom: String, currencyTo: String, amount: Int) =
        Result(apiService.getConvertedValue(currencyFrom, currencyTo, amount))

    override suspend fun getAllCurrencies() =
        currenciesDao.getCurrencies()

    override suspend fun addCurrencyInFavorite(currency: CurrenciesEntity) {
        currenciesDao.insert(currency)
    }

    override suspend fun deleteCurrency(currency: CurrenciesEntity) {
        currenciesDao.deleteCurrency(currency)
    }
}