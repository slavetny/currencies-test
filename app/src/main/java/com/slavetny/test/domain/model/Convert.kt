package com.slavetny.test.domain.model

data class Convert(
    val date: String,
    val historical: Boolean,
    val info: Info,
    val query: Query,
    val result: Double,
    val success: Boolean
)

data class Info(
    val rate: Double
)

data class Query(
    val amount: Int,
    val from: String,
    val to: String
)