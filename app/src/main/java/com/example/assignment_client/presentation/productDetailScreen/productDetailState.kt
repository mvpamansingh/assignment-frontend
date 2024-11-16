package com.example.assignment_client.presentation.productDetailScreen

import com.example.assignment_client.domain.models.Product

data class ProductDetailState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)