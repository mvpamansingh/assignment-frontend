package com.example.assignment_client.presentation.auth.signup

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val userId: String = ""
)

