package com.example.assignment_client.domain.repository

import com.example.assignment_client.data.remote.AppApis
import com.example.assignment_client.domain.models.GetProductRequest
import com.example.assignment_client.domain.models.Product
import com.example.assignment_client.domain.models.SignUpRequest
import com.example.assignment_client.domain.models.SignUpResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response



import kotlinx.coroutines.flow.flow





class ApiRepositoryImpl(
    private val appApi: AppApis
) : ApiRepository {

    override suspend fun getProductById(productId: String, userId: String): Flow<Result<Product>> = flow {
        try {
            val request = GetProductRequest(productId, userId)
            val response = appApi.getProductById(request)
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception("Failed to get product: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): Flow<Result<SignUpResponse>> = flow {
        try {
            val request = SignUpRequest(username, email, password)
            val response = appApi.signUp(request)
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception("Signup failed: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}

