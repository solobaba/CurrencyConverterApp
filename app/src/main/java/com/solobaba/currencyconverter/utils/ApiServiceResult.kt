package com.solobaba.currencyconverter.utils

sealed class ApiServiceResult<out T> {
    data class Success<T>(
        val data: T,
    ) : ApiServiceResult<T>()

    data class Error(
        val message: String,
        val code: Int? = null,
    ) : ApiServiceResult<Nothing>()

    data object Loading : ApiServiceResult<Nothing>()
}
