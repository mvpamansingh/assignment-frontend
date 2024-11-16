package com.example.assignment_client.domain.repository

import com.example.assignment_client.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ApiRepository {


    suspend fun getProductById(productId: String, userId: String): Flow<Result<Product>>

}