package com.example.testprojectsalfa.di.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModels: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass] ?:
        throw IllegalArgumentException("Viewmodel $modelClass not found")
        return viewModelProvider.get() as T
    }
}