package com.example.assignment_client.presentation.createProducts

import android.net.Uri

sealed class CreateProductEvent {
    data class TitleChanged(val title: String) : CreateProductEvent()
    data class DescriptionChanged(val description: String) : CreateProductEvent()
    data class TagsChanged(val tags: String) : CreateProductEvent()
    data class CompanyChanged(val company: String) : CreateProductEvent()
    data class CarTypeChanged(val carType: String) : CreateProductEvent()
    data class DealerChanged(val dealer: String) : CreateProductEvent()
    data class ImagesSelected(val uris: List<Uri>) : CreateProductEvent()
    object SubmitClicked : CreateProductEvent()
    object PickImages : CreateProductEvent()
}