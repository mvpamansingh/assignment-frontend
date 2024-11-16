package com.example.assignment_client.presentation.createProducts

import android.net.Uri


data class CreateProductState(
    val title: String = "",
    val description: String = "",
    val tags: List<String> = emptyList(),
    val company: String = "",
    val carType: String = "",
    val dealer: String = "",
    val selectedImages: List<Uri> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

