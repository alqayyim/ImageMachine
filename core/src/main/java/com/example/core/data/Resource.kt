package com.example.core.data

sealed class Resource<out T> {
    data class Success<T>(val data: T? = null) : Resource<T>()
    data class Error(val error: Throwable) : Resource<Nothing>()
    data class Loading(val isLoading: Boolean) : Resource<Nothing>()
}
