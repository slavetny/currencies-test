package com.slavetny.test.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
class CurrenciesEntity {
    @PrimaryKey
    var name: String = ""
}