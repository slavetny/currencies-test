package com.slavetny.test.domain.model

data class Currencies (
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean
)