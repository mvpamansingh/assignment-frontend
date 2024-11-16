package com.example.assignment_client.presentation

import android.app.Application
import com.example.assignment_client.presentation.auth.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }

    viewModel { SignUpViewModel(get()) }
}

