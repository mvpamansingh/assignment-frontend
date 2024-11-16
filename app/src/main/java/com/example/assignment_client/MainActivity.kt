package com.example.assignment_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment_client.presentation.ProductScreen
import com.example.assignment_client.presentation.ProductViewModel
import com.example.assignment_client.ui.theme.AssignmentclientTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentclientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {




                    val viewModel:ProductViewModel = koinViewModel()
                    ProductScreen(viewModel = viewModel, productId = "673816857f5869f85489ca32", userId ="6738162a7f5869f85489ca2e" )

                }
            }
        }
    }
}

