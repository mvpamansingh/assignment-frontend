package com.example.assignment_client.presentation.allProductScreen




sealed class AllProductsEvent {
    data class LoadProducts(val userId: String) : AllProductsEvent()
    data class RefreshProducts(val userId: String) : AllProductsEvent()
}