package com.example.assignment_client.presentation.productDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_client.domain.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val repository: ApiRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProductDetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.LoadProduct -> {
                loadProduct(event.productId, event.userId)
            }
        }
    }

    private fun loadProduct(productId: String, userId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            repository.getProductById(productId, userId).collect { result ->
                _state.value = _state.value.copy(isLoading = false)
                result.onSuccess { product ->
                    _state.value = _state.value.copy(product = product)
                }.onFailure { error ->
                    _state.value = _state.value.copy(error = error.message)
                }
            }
        }
    }
}