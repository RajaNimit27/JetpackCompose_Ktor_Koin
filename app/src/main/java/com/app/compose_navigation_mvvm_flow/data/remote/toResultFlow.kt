package com.app.compose_navigation_mvvm_flow.data.remote


import com.app.compose_navigation_mvvm_flow.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> toResultFlow(call: suspend () -> T?) : Flow<UiState<T?>> {
    return flow<UiState<T?>> {
        emit(UiState.Loading)
        val c = call.invoke()
        c.let { response ->
            try {
                println("response${response}")
                emit(UiState.Success(response))
            } catch (e: Exception) {
                  emit(UiState.Error(e.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)
}
