package com.example.core

import com.example.core.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

fun <T> Flow<Resource<T>>.buildFlow() =
    onStart {
        emit(Resource.Loading(true))
    }.onCompletion {
        emit(Resource.Loading(false))
    }.
    catch { error ->
        emit(Resource.Error(Throwable(message = error.message)))
        emit(Resource.Loading(false))
    }
        .flowOn(Dispatchers.IO)
