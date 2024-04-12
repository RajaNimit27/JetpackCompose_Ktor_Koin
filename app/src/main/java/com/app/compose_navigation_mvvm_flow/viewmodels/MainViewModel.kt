package com.app.compose_navigation_mvvm_flow.viewmodels

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.app.compose_navigation_mvvm_flow.data.Receipes
import com.app.compose_navigation_mvvm_flow.data.Repository
import com.app.compose_navigation_mvvm_flow.utils.UiState
import com.app.compose_navigation_mvvm_flow.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository, application: Application): BaseViewModel(application) {


    val _uiStateReceipeList = MutableStateFlow<UiState<Receipes>>(UiState.Loading)
    val uiStateReceipeList: StateFlow<UiState<Receipes>> = _uiStateReceipeList

    val _uiStateReceipeDetail = MutableStateFlow<UiState<Receipes.Recipe>>(UiState.Loading)
    val uiStateReceipeDetail: StateFlow<UiState<Receipes.Recipe>> = _uiStateReceipeDetail

    fun getReceipesList() = viewModelScope.launch {
        repository.getReceipes(context).collect {
            when (it) {
                is UiState.Success -> {
                    _uiStateReceipeList.value = UiState.Success(it.data)
                }
                is UiState.Loading -> {
                    _uiStateReceipeList.value = UiState.Loading
                }
                is UiState.Error -> {
                    //Handle Error
                    _uiStateReceipeList.value = UiState.Error(it.message)
                }
            }
        }
    }

    fun getReceipeDetail(id:Int?) = viewModelScope.launch {
        repository.getReceipesDetail(context,id).collect {
            when (it) {
                is UiState.Success -> {
                    _uiStateReceipeDetail.value = UiState.Success(it.data)
                }
                is UiState.Loading -> {
                    _uiStateReceipeDetail.value = UiState.Loading
                }
                is UiState.Error -> {
                    //Handle Error
                    _uiStateReceipeDetail.value = UiState.Error(it.message)
                }
            }
        }
    }

}