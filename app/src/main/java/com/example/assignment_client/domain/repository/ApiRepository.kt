package com.example.assignment_client.domain.repository

import android.net.Uri
import com.example.assignment_client.domain.models.CreateProductResponse
import com.example.assignment_client.domain.models.DeleteProductResponse
import com.example.assignment_client.domain.models.Product
import com.example.assignment_client.domain.models.SignInResponse
import com.example.assignment_client.domain.models.SignUpResponse
import com.example.assignment_client.domain.models.UpdateProductResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {


    suspend fun getProductById(productId: String, userId: String): Flow<Result<Product>>


    suspend fun signUp(username: String, email: String, password: String): Flow<Result<SignUpResponse>>


    suspend fun signIn(email: String, password: String): Flow<Result<SignInResponse>>

    suspend fun getAllProducts(userId: String): Flow<Result<List<Product>>>


    suspend fun createProduct(
        createdBy: String,
        title: String,
        description: String,
        tags: List<String>,
        company: String,
        carType: String?,
        dealer: String?,
        images: List<Uri>
    ): Flow<Result<CreateProductResponse>>

    suspend fun deleteProduct(productId: String, userId: String): Flow<Result<DeleteProductResponse>>



    suspend fun updateProduct(
        productId: String,
        title: String?,
        description: String?,
        tags: List<String>?,
        company: String?,
        carType: String?,
        dealer: String?,
        images: List<Uri>?
    ): Flow<Result<UpdateProductResponse>>
}