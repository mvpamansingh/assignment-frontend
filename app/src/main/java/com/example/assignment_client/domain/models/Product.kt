package com.example.assignment_client.domain.models

data class Product(
    val _id: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val company: String,
    val carType: String?,
    val dealer: String?,
    val carImages: List<CarImage>,
    val createdBy: String
)

data class CarImage(
    val url: String,
    val publicId: String
)

data class GetProductRequest(
    val productId: String,
    val userId: String
)