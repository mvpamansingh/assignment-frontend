package com.example.assignment_client.presentation.auth.signin

sealed class SignInEvent {

    data class EmailChanged(val email: String) : SignInEvent()
    data class PasswordChanged(val password: String) : SignInEvent()
    object SignInClicked : SignInEvent()
}