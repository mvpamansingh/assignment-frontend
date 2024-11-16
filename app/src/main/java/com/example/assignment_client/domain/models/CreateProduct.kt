package com.example.assignment_client.domain.models




data class CreateProductRequest(
    val createdBy: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val company: String,
    val carType: String?,
    val dealer: String?
)

data class CreateProductResponse(
    val message: String,
    val product: Product
)