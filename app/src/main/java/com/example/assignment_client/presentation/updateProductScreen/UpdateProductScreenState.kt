package com.example.assignment_client.presentation.updateProductScreen

import android.net.Uri
import com.example.assignment_client.domain.models.CarImage


data class UpdateProductState(
    val productId: String = "",
    val title: String = "",
    val description: String = "",
    val tags: List<String> = emptyList(),
    val company: String = "",
    val carType: String = "",
    val dealer: String = "",
    val selectedImages: List<Uri> = emptyList(),
    val existingImages: List<CarImage> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)