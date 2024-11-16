package com.example.assignment_client.domain.models

data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String
)

data class SignUpResponse(
    val message: String,
    val userId: String
)