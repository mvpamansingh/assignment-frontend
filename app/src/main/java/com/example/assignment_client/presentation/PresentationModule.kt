package com.example.assignment_client.presentation

import android.app.Application
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductViewModel(get()) } // Add any dependencies your ViewModel needs in the constructor
}

