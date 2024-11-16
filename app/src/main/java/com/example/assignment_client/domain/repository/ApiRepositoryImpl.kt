package com.example.assignment_client.domain.repository

import android.net.Uri
import android.util.Log
import com.example.assignment_client.data.remote.AppApis
import com.example.assignment_client.domain.models.CreateProductResponse
import com.example.assignment_client.domain.models.GetAllProductsRequest
import com.example.assignment_client.domain.models.GetProductRequest
import com.example.assignment_client.domain.models.Product
import com.example.assignment_client.domain.models.SignInRequest
import com.example.assignment_client.domain.models.SignInResponse
import com.example.assignment_client.domain.models.SignUpRequest
import com.example.assignment_client.domain.models.SignUpResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import android.content.Context
import com.example.assignment_client.domain.models.DeleteProductRequest
import com.example.assignment_client.domain.models.DeleteProductResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody


import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody


class ApiRepositoryImpl(
    private val appApi: AppApis,
    private val context: Context
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

    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<Result<SignInResponse>> = flow {
        try {
            val request = SignInRequest(email, password)
            val response = appApi.signIn(request)
            if (response.isSuccessful && response.body() != null) {
                Log.d("heloo", "ver")
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception("SignIn failed: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getAllProducts(userId: String): Flow<Result<List<Product>>> = flow {
        try {
            val request = GetAllProductsRequest(userId)
            val response = appApi.getAllProducts(request)
            if (response.isSuccessful && response.body() != null) {
                Log.d("heloo", "list fetched correctly")
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception("Failed to get products: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun createProduct(
        createdBy: String,
        title: String,
        description: String,
        tags: List<String>,
        company: String,
        carType: String?,
        dealer: String?,
        images: List<Uri>): Flow<Result<CreateProductResponse>> = flow {
        try {
            val createdByBody = createdBy.toRequestBody("text/plain".toMediaType())
            val titleBody = title.toRequestBody("text/plain".toMediaType())
            val descriptionBody = description.toRequestBody("text/plain".toMediaType())
            val tagsBody = Gson().toJson(tags).toRequestBody("text/plain".toMediaType())
            val companyBody = company.toRequestBody("text/plain".toMediaType())
            val carTypeBody = carType?.toRequestBody("text/plain".toMediaType())
            val dealerBody = dealer?.toRequestBody("text/plain".toMediaType())

            val imageParts = images.map { uri ->
                val stream = context.contentResolver.openInputStream(uri)
                val mimeType = context.contentResolver.getType(uri) ?: "image/jpeg"
                val request = stream?.readBytes()?.toRequestBody(mimeType.toMediaType())
                MultipartBody.Part.createFormData(
                    "carImages",
                    "image_${System.currentTimeMillis()}.${mimeType.substringAfter("/")}",
                    request!!
                )
            }

            val response = appApi.createProduct(
                createdByBody,
                titleBody,
                descriptionBody,
                tagsBody,
                companyBody,
                carTypeBody,
                dealerBody,
                imageParts
            )

            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception("Failed to create product: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun deleteProduct(
        productId: String,
        userId: String
    ): Flow<Result<DeleteProductResponse>> = flow {
        try {
            val request = DeleteProductRequest(productId, userId)
            val response = appApi.deleteProduct(request)
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception("Failed to delete product: ${response.errorBody()?.string()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}

