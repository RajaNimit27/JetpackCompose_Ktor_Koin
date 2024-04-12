package com.app.compose_navigation_mvvm_flow.di

import com.app.compose_navigation_mvvm_flow.data.remote.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule= module {
    factory {  RemoteDataSource(get()) }
}
