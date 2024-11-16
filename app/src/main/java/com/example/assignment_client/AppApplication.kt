package com.example.assignment_client

import android.app.Application
import com.example.assignment_client.data.di.dataModule
import com.example.assignment_client.presentation.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application(){


    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                dataModule , viewModelModule
            )
            androidContext(this@AppApplication)
        }

    }
}