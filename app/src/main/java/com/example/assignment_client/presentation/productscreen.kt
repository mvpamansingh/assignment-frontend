package com.example.assignment_client.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun ProductScreen(
    viewModel: ProductViewModel,
    productId: String,
    userId: String
) {
    val product by viewModel.product.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProduct(productId, userId)
    }

    product?.onSuccess { product ->
        // Display product details
    }?.onFailure { error ->
        // Show error
    }
}