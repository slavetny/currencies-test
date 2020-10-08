package com.slavetny.test.data.db

import androidx.room.*

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: CurrenciesEntity)

    @Query("SELECT * FROM currencies")
    fun getCurrencies(): List<String>

    @Delete
    fun deleteCurrency(currency: CurrenciesEntity)
}