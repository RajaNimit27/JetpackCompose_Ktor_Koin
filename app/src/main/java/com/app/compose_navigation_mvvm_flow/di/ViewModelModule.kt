package com.app.compose_navigation_mvvm_flow.di

import com.app.compose_navigation_mvvm_flow.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule= module {
    viewModel{ MainViewModel(get(),get()) }
}


