package com.example.assignment_client.presentation.auth.signup

sealed class SignUpEvent {
    data class UsernameChanged(val username: String) : SignUpEvent()

    data class EmailChanged(val email: String) : SignUpEvent()

    data class PasswordChanged(val password: String) : SignUpEvent()

    object SignUpClicked : SignUpEvent()
}