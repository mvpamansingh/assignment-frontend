package com.example.assignment_client.data.remote

import com.example.assignment_client.domain.models.GetProductRequest
import com.example.assignment_client.domain.models.Product
import com.example.assignment_client.domain.models.SignUpRequest
import com.example.assignment_client.domain.models.SignUpResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppApis {

    @POST("getProductById")
    suspend fun getProductById(
        @Body request: GetProductRequest
    ): Response<Product>

    @POST("signup")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): Response<SignUpResponse>


}