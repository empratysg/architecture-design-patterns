package com.example.architecturedesignpattern

import android.app.Application
import com.example.architecturedesignpattern.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.java.KoinAndroidApplication
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidContext(this@MainApp)
            modules(
                repositoryModules,
                useCaseModules,
                networkModules,
                localModules,
                viewModels,
                modelModules
            )
        }
    }
}