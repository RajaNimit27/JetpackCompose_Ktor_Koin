package com.app.compose_navigation_mvvm_flow.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel


open class BaseViewModel(application: Application) : AndroidViewModel(application) {
  protected val context
    get() = getApplication<Application>()
}