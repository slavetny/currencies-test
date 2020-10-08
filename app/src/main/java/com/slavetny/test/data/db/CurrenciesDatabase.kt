package com.slavetny.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrenciesEntity::class], version = 2)
abstract class CurrenciesDatabase : RoomDatabase() {
    abstract fun currenciesDao(): CurrenciesDao
}