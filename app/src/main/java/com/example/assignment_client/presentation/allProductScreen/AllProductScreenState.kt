package com.example.assignment_client.presentation.allProductScreen

import com.example.assignment_client.domain.models.Product

data class AllProductsState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

