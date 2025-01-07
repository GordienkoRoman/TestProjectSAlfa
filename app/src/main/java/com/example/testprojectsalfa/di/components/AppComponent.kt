package com.example.testprojectsalfa.di.components

import android.content.Context
import com.example.testprojectsalfa.di.modules.AppModule
import com.example.testprojectsalfa.di.modules.ViewModelModule
import com.example.testprojectsalfa.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
annotation class AppScope

@AppScope
@Component(
    modules = [AppModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}