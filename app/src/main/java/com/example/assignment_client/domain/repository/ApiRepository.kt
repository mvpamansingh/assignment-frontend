package com.example.assignment_client.domain.repository

import com.example.assignment_client.domain.models.Product
import com.example.assignment_client.domain.models.SignInResponse
import com.example.assignment_client.domain.models.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {


    suspend fun getProductById(productId: String, userId: String): Flow<Result<Product>>


    suspend fun signUp(username: String, email: String, password: String): Flow<Result<SignUpResponse>>


    suspend fun signIn(email: String, password: String): Flow<Result<SignInResponse>>

    suspend fun getAllProducts(userId: String): Flow<Result<List<Product>>>



}