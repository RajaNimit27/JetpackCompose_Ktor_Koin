package com.app.compose_navigation_mvvm_flow.data

import android.content.Context
import com.app.compose_navigation_mvvm_flow.data.remote.RemoteDataSource
import com.app.compose_navigation_mvvm_flow.data.remote.toResultFlow
import com.app.compose_navigation_mvvm_flow.utils.UiState
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getReceipes(context: Context): Flow<UiState<Receipes?>> {
        return toResultFlow{
            remoteDataSource.getReceipes()
        }
    }

    suspend fun getReceipesDetail(context: Context,id:Int?): Flow<UiState<Receipes.Recipe?>> {
        return toResultFlow{
            remoteDataSource.getReceipesDetail(id)
        }


    }

}