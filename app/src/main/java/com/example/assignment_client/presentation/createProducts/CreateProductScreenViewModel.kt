package com.example.assignment_client.presentation.createProducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_client.domain.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CreateProductViewModel(
    private val repository: ApiRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CreateProductState())
    val state = _state.asStateFlow()

    fun onEvent(event: CreateProductEvent) {
        when (event) {
            is CreateProductEvent.TitleChanged -> {
                _state.value = _state.value.copy(title = event.title)
            }
            is CreateProductEvent.DescriptionChanged -> {
                _state.value = _state.value.copy(description = event.description)
            }
            is CreateProductEvent.TagsChanged -> {
                val tagsList = event.tags.split(",").map { it.trim() }
                _state.value = _state.value.copy(tags = tagsList)
            }
            is CreateProductEvent.CompanyChanged -> {
                _state.value = _state.value.copy(company = event.company)
            }
            is CreateProductEvent.CarTypeChanged -> {
                _state.value = _state.value.copy(carType = event.carType)
            }
            is CreateProductEvent.DealerChanged -> {
                _state.value = _state.value.copy(dealer = event.dealer)
            }
            is CreateProductEvent.ImagesSelected -> {
                _state.value = _state.value.copy(selectedImages = event.uris)
            }
            CreateProductEvent.SubmitClicked -> {
                createProduct()
            }
            else -> {}
        }
    }

    private fun createProduct() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repository.createProduct(
                createdBy = "6738162a7f5869f85489ca2e",
                title = _state.value.title,
                description = _state.value.description,
                tags = _state.value.tags,
                company = _state.value.company,
                carType = _state.value.carType.takeIf { it.isNotBlank() },
                dealer = _state.value.dealer.takeIf { it.isNotBlank() },
                images = _state.value.selectedImages
            ).collect { result ->
                _state.value = _state.value.copy(isLoading = false)
                result.onSuccess {
                    _state.value = _state.value.copy(isSuccess = true)
                }.onFailure { error ->
                    _state.value = _state.value.copy(error = error.message)
                }
            }
        }
    }
}