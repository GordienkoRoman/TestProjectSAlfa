package com.example.testprojectsalfa.di.modules

import androidx.lifecycle.ViewModel
import com.example.testprojectsalfa.di.viewModelFactory.ViewModelKey
import com.example.testprojectsalfa.presentation.mainScreen.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface  ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindUserListViewModel(viewModel: MainScreenViewModel): ViewModel
}