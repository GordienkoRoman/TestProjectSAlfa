package com.example.testprojectsalfa.di.modules

import androidx.lifecycle.ViewModel
import com.example.testprojectsalfa.di.viewModelFactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface  ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(UserListViewModel::class)
//    fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel
}