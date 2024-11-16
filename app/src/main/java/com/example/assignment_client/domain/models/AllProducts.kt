package com.example.assignment_client.domain.models



data class GetAllProductsRequest(
    val userId: String
)

data class GetAllProductsResponse(
    val products: List<Product>
)