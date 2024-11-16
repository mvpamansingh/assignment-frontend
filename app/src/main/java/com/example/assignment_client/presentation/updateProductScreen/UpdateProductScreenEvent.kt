package com.example.assignment_client.presentation.updateProductScreen

import android.net.Uri


sealed class UpdateProductEvent {
    data class TitleChanged(val title: String) : UpdateProductEvent()
    data class DescriptionChanged(val description: String) : UpdateProductEvent()
    data class TagsChanged(val tags: String) : UpdateProductEvent()
    data class CompanyChanged(val company: String) : UpdateProductEvent()
    data class CarTypeChanged(val carType: String) : UpdateProductEvent()
    data class DealerChanged(val dealer: String) : UpdateProductEvent()
    data class ImagesSelected(val uris: List<Uri>) : UpdateProductEvent()
    object SubmitClicked : UpdateProductEvent()
    object LoadProduct : UpdateProductEvent()
}