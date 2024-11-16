@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.assignment_client.presentation.productDetailScreen
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.assignment_client.domain.models.Product
import org.koin.androidx.compose.koinViewModel
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import org.koin.core.parameter.parametersOf


//@Composable
//fun ProductDetailScreen(
//    productId: String,
//    userId: String,
//    viewModel: ProductDetailViewModel = koinViewModel(),
//    onBackClick: () -> Unit
//) {
//    val state by viewModel.state.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.onEvent(ProductDetailEvent.LoadProduct(productId, userId))
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Product Details") },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(Icons.Default.ArrowBack, "Back")
//                    }
//                }
//            )
//        }
//    ) { padding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            when {
//                state.isLoading -> {
//                    CircularProgressIndicator(
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//                state.error != null -> {
//                    Text(
//                        text = state.error ?: "Unknown error",
//                        color = MaterialTheme.colorScheme.error,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//                state.product != null -> {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .verticalScroll(rememberScrollState())
//                            .padding(16.dp)
//                    ) {
//                        Text(
//                            text = state.product!!.title,
//                            style = MaterialTheme.typography.headlineMedium
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = state.product!!.description,
//                            style = MaterialTheme.typography.bodyLarge
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Text(
//                            text = "Company: ${state.product!!.company}",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                        Text(
//                            text = "Car Type: ${state.product!!.carType ?: "N/A"}",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                        Text(
//                            text = "Dealer: ${state.product!!.dealer ?: "N/A"}",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "Tags: ${state.product!!.tags.joinToString(", ")}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        LazyRow(
//                            horizontalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            items(state.product!!.carImages) { image ->
//                                AsyncImage(
//                                    model = image.url,
//                                    contentDescription = null,
//                                    modifier = Modifier.size(200.dp)
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    userId: String,
    viewModel: ProductDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ProductDetailEvent.LoadProduct(productId, userId))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Car Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                state.error != null -> {
                    Text(
                        text = state.error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.product != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        // Images Section
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            LazyRow(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(8.dp)
                            ) {
                                items(state.product!!.carImages) { image ->
                                    Card(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .aspectRatio(4f/3f),
                                        elevation = CardDefaults.cardElevation(2.dp)
                                    ) {
                                        AsyncImage(
                                            model = image.url,
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Details Card
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                DetailRow(
                                    icon = Icons.Default.TimeToLeave,
                                    label = "Title",
                                    value = state.product!!.title
                                )

                                Divider(modifier = Modifier.padding(vertical = 8.dp))

                                DetailRow(
                                    icon = Icons.Default.Description,
                                    label = "Description",
                                    value = state.product!!.description
                                )

                                Divider(modifier = Modifier.padding(vertical = 8.dp))

                                DetailRow(
                                    icon = Icons.Default.Business,
                                    label = "Company",
                                    value = state.product!!.company
                                )

                                Divider(modifier = Modifier.padding(vertical = 8.dp))

                                DetailRow(
                                    icon = Icons.Default.LocalShipping,
                                    label = "Car Type",
                                    value = state.product!!.carType ?: "N/A"
                                )

                                Divider(modifier = Modifier.padding(vertical = 8.dp))

                                DetailRow(
                                    icon = Icons.Default.Store,
                                    label = "Dealer",
                                    value = state.product!!.dealer ?: "N/A"
                                )

                                Divider(modifier = Modifier.padding(vertical = 8.dp))

                                Column {
                                    Text(
                                        text = "Tags",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    FlowRow(
                                        modifier = Modifier.padding(top = 8.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        state.product!!.tags.forEach { tag ->
                                            SuggestionChip(
                                                onClick = { },
                                                label = { Text(tag) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}