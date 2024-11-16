package com.example.assignment_client.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_client.domain.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SignUpViewModel(
    private val repository: ApiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.UsernameChanged -> {
                _state.value = _state.value.copy(username = event.username)
            }
            is SignUpEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.email)
            }
            is SignUpEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.password)
            }
            SignUpEvent.SignUpClicked -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true, error = null)
            repository.signUp(
                _state.value.username,
                _state.value.email,
                _state.value.password
            ).collect { result ->

                _state.value = _state.value.copy(isLoading = false)
                result.onSuccess {reponse->
                    _state.value = _state.value.copy(isSuccess = true,
                        userId = reponse.userId,//isSuccess = true,
                    )
                }.onFailure { error ->
                    _state.value = _state.value.copy(error = error.message)
                }

            }
        }
    }
}