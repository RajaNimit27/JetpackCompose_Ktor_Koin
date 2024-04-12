package com.app.compose_navigation_mvvm_flow.data.remote


import android.content.Context
import com.app.compose_navigation_mvvm_flow.utils.Constants
import com.app.compose_navigation_mvvm_flow.utils.UiState
import com.app.compose_navigation_mvvm_flow.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> toResultFlow(context: Context, call: suspend () -> T?) : Flow<UiState<T?>> {
    return flow<UiState<T?>> {
        if(Utils.hasInternetConnection(context)) {
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
        }else{
            emit(UiState.Error(Constants.API_INTERNET_MESSAGE))
        }
    }.flowOn(Dispatchers.IO)
}
