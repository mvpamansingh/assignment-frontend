package com.example.assignment_client.presentation.allProductScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_client.domain.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AllProductsViewModel(
    private val repository: ApiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AllProductsState())
    val state = _state.asStateFlow()

    fun onEvent(event: AllProductsEvent) {
        when (event) {
            is AllProductsEvent.LoadProducts -> {
                loadProducts(event.userId)
            }
        }
    }

    private fun loadProducts(userId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repository.getAllProducts(userId).collect { result ->
                _state.value = _state.value.copy(isLoading = false)
                result.onSuccess { products ->
                    _state.value = _state.value.copy(products = products)
                }.onFailure { error ->
                    _state.value = _state.value.copy(error = error.message)
                }
            }
        }
    }
}