package com.example.assignment_client.presentation

import android.app.Application
import com.example.assignment_client.presentation.allProductScreen.AllProductsViewModel
import com.example.assignment_client.presentation.auth.signin.SignInViewModel
import com.example.assignment_client.presentation.auth.signup.SignUpViewModel
import com.example.assignment_client.presentation.createProducts.CreateProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }

    viewModel { SignUpViewModel(get()) }




    viewModel { SignInViewModel(get()) }

    viewModel { AllProductsViewModel(get()) }

    viewModel{ CreateProductViewModel(get())}
}

