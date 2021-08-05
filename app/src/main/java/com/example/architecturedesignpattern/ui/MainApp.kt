package com.example.architecturedesignpattern.ui

import android.app.Application
import com.example.architecturedesignpattern.di.*
import org.koin.android.java.KoinAndroidApplication
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin( KoinAndroidApplication.create(this).modules(
            repositoryModules,
            useCaseModules,
            networkModules,
            localModules,
            viewModels,
            modelModules
        ))
    }
}