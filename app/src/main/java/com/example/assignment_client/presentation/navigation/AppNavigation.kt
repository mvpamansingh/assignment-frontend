package com.example.assignment_client.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment_client.data.di.UserPreferences
import com.example.assignment_client.data.di.UserPreferencesRepository
import com.example.assignment_client.presentation.allProductScreen.AllProductsScreen
import com.example.assignment_client.presentation.auth.signin.SignInScreen
import com.example.assignment_client.presentation.auth.signup.SignUpScreen
import com.example.assignment_client.presentation.createProducts.CreateProductScreen
import com.example.assignment_client.presentation.productDetailScreen.ProductDetailScreen
import com.example.assignment_client.presentation.updateProductScreen.UpdateProductScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    userPreferencesRepository: UserPreferencesRepository
) {
    val userPreferences by userPreferencesRepository.userPreferencesFlow.collectAsState(
        initial = UserPreferences()
    )

    LaunchedEffect(userPreferences.userId) {
        if (userPreferences.userId.isBlank()) {
            navController.navigate(Screen.SignIn.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    val startDestination = if (userPreferences.userId.isNotBlank()) {
        Screen.AllProducts.route
    } else {
        Screen.SignIn.route
    }

    NavHost(navController = navController, startDestination = startDestination) {


        composable(Screen.SignIn.route) {
            var shouldNavigate by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(shouldNavigate) {
                shouldNavigate?.let { userId ->
                    userPreferencesRepository.updateUserId(userId)
                    navController.navigate(Screen.AllProducts.route) {
                        popUpTo(Screen.SignIn.route) { inclusive = true }
                    }
                    shouldNavigate = null
                }
            }

            SignInScreen(
                onSignInSuccess = { userId ->
                    shouldNavigate = userId
                },
                onSignUpClick = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }

        composable(Screen.SignUp.route) {
            var shouldNavigate by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(shouldNavigate) {
                shouldNavigate?.let { userId ->
                    userPreferencesRepository.updateUserId(userId)
                    navController.navigate(Screen.AllProducts.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                    shouldNavigate = null
                }
            }

            SignUpScreen(
                onSignUpSuccess = { userId ->
                    shouldNavigate = userId
                }
            )
        }

        composable(Screen.AllProducts.route) {
            AllProductsScreen(
                userId = userPreferences.userId,
                onProductClick = { productId ->
                    navController.navigate("${Screen.ProductDetail.route}/$productId")
                },
                onCreateProductClick = {
                    navController.navigate(Screen.CreateProduct.route)
                },
                onEditClick = { productId ->  // Add this handler
                    navController.navigate("${Screen.UpdateProduct.route}/$productId")
                },
            )
        }

        composable(
            route = "${Screen.UpdateProduct.route}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            UpdateProductScreen(
                productId = productId,
                userId = userPreferences.userId,
                onSuccess = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.CreateProduct.route) {
            CreateProductScreen(
                onSuccess = {
                    navController.popBackStack()
                },
                userId = userPreferences.userId,
            )
        }



        composable(
            route = "${Screen.ProductDetail.route}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            ProductDetailScreen(
                productId = productId,
                userId = userPreferences.userId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}