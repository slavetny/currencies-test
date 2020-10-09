package com.slavetny.test.data.network

data class Result<out T>(
    val value: T?,
    val throwable: Throwable? = null
)