package com.example.assignment_client.domain.models

// Add to your models package
data class DeleteProductRequest(
    val productId: String,
    val userId: String
)

data class DeleteProductResponse(
    val message: String
)