package com.example.testprojectsalfa

import android.app.Application
import com.example.testprojectsalfa.di.components.AppComponent
import com.example.testprojectsalfa.di.components.DaggerAppComponent

class BaseApplication  : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

}