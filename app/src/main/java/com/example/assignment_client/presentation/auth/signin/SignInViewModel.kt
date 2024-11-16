package com.example.assignment_client.presentation.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_client.domain.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val repository: ApiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.email)
            }
            is SignInEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.password)
            }
            SignInEvent.SignInClicked -> {
                signIn()
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repository.signIn(
                _state.value.email,
                _state.value.password
            ).collect { result ->
                _state.value = _state.value.copy(isLoading = false)
                result.onSuccess {
                    _state.value = _state.value.copy(isSuccess = true)
                }.onFailure { error ->
                    _state.value = _state.value.copy(error = error.message)
                }
            }
        }
    }
}