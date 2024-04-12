package com.app.compose_navigation_mvvm_flow.di

import com.app.compose_navigation_mvvm_flow.data.Repository
import org.koin.dsl.module


val repositoryModule = module {
    factory {  Repository(get()) }
}