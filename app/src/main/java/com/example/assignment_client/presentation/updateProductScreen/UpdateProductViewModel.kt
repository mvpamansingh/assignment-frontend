package com.example.assignment_client.presentation.updateProductScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_client.domain.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class UpdateProductViewModel(
    private val repository: ApiRepository,
    private val productId: String,
    private val userId: String
) : ViewModel() {
    private val _state = MutableStateFlow(UpdateProductState())
    val state = _state.asStateFlow()

    fun onEvent(event: UpdateProductEvent) {
        when (event) {
            is UpdateProductEvent.TitleChanged -> {
                _state.value = _state.value.copy(title = event.title)
            }
            is UpdateProductEvent.DescriptionChanged -> {
                _state.value = _state.value.copy(description = event.description)
            }
            is UpdateProductEvent.TagsChanged -> {
                _state.value = _state.value.copy(
                    tags = event.tags.split(",").map { it.trim() }
                )
            }
            is UpdateProductEvent.CompanyChanged -> {
                _state.value = _state.value.copy(company = event.company)
            }
            is UpdateProductEvent.CarTypeChanged -> {
                _state.value = _state.value.copy(carType = event.carType)
            }
            is UpdateProductEvent.DealerChanged -> {
                _state.value = _state.value.copy(dealer = event.dealer)
            }
            is UpdateProductEvent.ImagesSelected -> {
                _state.value = _state.value.copy(selectedImages = event.uris)
            }
            UpdateProductEvent.SubmitClicked -> {
                updateProduct()
            }
            UpdateProductEvent.LoadProduct -> {
                loadProduct()
            }
        }
    }

    private fun loadProduct() {
        viewModelScope.launch {
            repository.getProductById(productId, userId).collect { result ->
                result.onSuccess { product ->
                    _state.value = _state.value.copy(
                        title = product.title,
                        description = product.description,
                        tags = product.tags,
                        company = product.company,
                        carType = product.carType ?: "",
                        dealer = product.dealer ?: "",
                        existingImages = product.carImages
                    )
                }.onFailure { error ->
                    _state.value = _state.value.copy(error = error.message)
                }
            }
        }
    }

    private fun updateProduct() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repository.updateProduct(
                productId = productId,
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