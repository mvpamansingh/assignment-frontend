package com.example.assignment_client.presentation.productDetailScreen


sealed class ProductDetailEvent {
    data class LoadProduct(val productId: String, val userId: String) : ProductDetailEvent()
}