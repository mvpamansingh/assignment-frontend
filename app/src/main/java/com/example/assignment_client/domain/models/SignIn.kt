package com.example.assignment_client.domain.models

data class SignInRequest(
    val email: String,
    val password: String
)

data class SignInResponse(
    val message: String,
    val userId: String,
    val username: String,
    val email: String
)