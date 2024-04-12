package com.app.compose_navigation_mvvm_flow.viewmodels

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.app.compose_navigation_mvvm_flow.data.Receipes
import com.app.compose_navigation_mvvm_flow.data.Repository
import com.app.compose_navigation_mvvm_flow.utils.UiState
import com.app.compose_navigation_mvvm_flow.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository, application: Application): BaseViewModel(application) {

    val _uiStateReceipeList = MutableStateFlow<UiState<Receipes?>>(UiState.Loading)
    val uiStateReceipeList: StateFlow<UiState<Receipes?>> = _uiStateReceipeList

    val _uiStateReceipeDetail = MutableStateFlow<UiState<Receipes.Recipe?>>(UiState.Loading)
    val uiStateReceipeDetail: StateFlow<UiState<Receipes.Recipe?>> = _uiStateReceipeDetail


    fun getReceipesList() = viewModelScope.launch {
        fetchData({ repository.getReceipes(context) }, _uiStateReceipeList)
    }

    fun getReceipeDetail(id: Int?) = viewModelScope.launch {
        fetchData({ repository.getReceipesDetail(context, id) }, _uiStateReceipeDetail)
    }
}