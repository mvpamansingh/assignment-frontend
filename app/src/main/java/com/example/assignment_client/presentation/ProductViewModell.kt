package com.example.assignment_client.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_client.domain.models.Product
import com.example.assignment_client.domain.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ApiRepository
) : ViewModel() {

    private val _product = MutableStateFlow<Result<Product>?>(null)
    val product = _product.asStateFlow()

    fun getProduct(productId: String, userId: String) {
        viewModelScope.launch {

            repository.getProductById(productId, userId).collect { result ->
                _product.value = result
            }
            Log.d("hell", product.value.toString())
        }
    }
}