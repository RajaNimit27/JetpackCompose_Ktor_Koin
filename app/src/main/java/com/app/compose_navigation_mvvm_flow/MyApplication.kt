package com.app.compose_navigation_mvvm_flow

import android.app.Application
import com.app.compose_navigation_mvvm_flow.di.apiServiceModule
import com.app.compose_navigation_mvvm_flow.di.networkModule
import com.app.compose_navigation_mvvm_flow.di.remoteDataSourceModule
import com.app.compose_navigation_mvvm_flow.di.repositoryModule
import com.app.compose_navigation_mvvm_flow.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(networkModule,
                apiServiceModule, remoteDataSourceModule, repositoryModule, viewModelModule)
        }
    }
}
