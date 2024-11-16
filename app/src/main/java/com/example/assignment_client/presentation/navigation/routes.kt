package com.example.assignment_client.presentation.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object AllProducts : Screen("allProducts")
    object UpdateProduct : Screen("updateProduct/{productId}")
    object CreateProduct : Screen("createProduct")
}