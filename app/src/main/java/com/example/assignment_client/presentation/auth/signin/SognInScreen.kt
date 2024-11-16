package com.example.assignment_client.presentation.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

//@Composable
//fun SignInScreen(
//    viewModel: SignInViewModel = koinViewModel(),
//    onSignInSuccess: (String) -> Unit,
//    onSignUpClick: () -> Unit
//    ) {
//    val state by viewModel.state.collectAsState()
//
//    LaunchedEffect(state.isSuccess) {
//        if (state.isSuccess) {
//            onSignInSuccess(state.userId)
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        TextField(
//            value = state.email,
//            onValueChange = { viewModel.onEvent(SignInEvent.EmailChanged(it)) },
//            label = { Text("Email") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        TextField(
//            value = state.password,
//            onValueChange = { viewModel.onEvent(SignInEvent.PasswordChanged(it)) },
//            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { viewModel.onEvent(SignInEvent.SignInClicked) },
//            enabled = !state.isLoading,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if (state.isLoading) {
//                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
//            } else {
//                Text("Sign In")
//            }
//        }
//
//        state.error?.let { error ->
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = error,
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//    }
//}

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel(),
    onSignInSuccess: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onSignInSuccess(state.userId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(SignInEvent.EmailChanged(it)) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(SignInEvent.PasswordChanged(it)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onEvent(SignInEvent.SignInClicked) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Sign In")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?")
            TextButton(onClick = onSignUpClick) {
                Text("Sign Up", color = MaterialTheme.colorScheme.primary)
            }
        }

        state.error?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}