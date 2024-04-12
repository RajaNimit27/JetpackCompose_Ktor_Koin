package com.app.compose_navigation_mvvm_flow.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.compose_navigation_mvvm_flow.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


open class BaseViewModel(application: Application) : AndroidViewModel(application) {
  protected val context
    get() = getApplication<Application>()

  suspend fun <T> fetchData(uiStateFlow: MutableStateFlow<UiState<T?>>, apiCall: suspend () -> Flow<UiState<T?>>) {
      uiStateFlow.value = UiState.Loading
    try {
       apiCall().collect {
         uiStateFlow.value = it
      }
    } catch (e: Exception) {
       uiStateFlow.value = UiState.Error(e.message?:"")
    }
  }
}