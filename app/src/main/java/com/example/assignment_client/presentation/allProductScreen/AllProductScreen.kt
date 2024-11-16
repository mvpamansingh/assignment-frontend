@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.assignment_client.presentation.allProductScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.TimeToLeave
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.assignment_client.domain.models.Product
import org.koin.androidx.compose.koinViewModel



//@Composable
//fun AllProductsScreen(
//    viewModel: AllProductsViewModel = koinViewModel(),
//    userId: String,
//    onProductClick: (String) -> Unit,
//    onCreateProductClick: () -> Unit,
//    onEditClick: (String) -> Unit,
//) {
//    val state by viewModel.state.collectAsState()
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    LaunchedEffect(userId) {
//        if (userId.isNotBlank()) {
//            viewModel.onEvent(AllProductsEvent.LoadProducts(userId))
//        }
//    }
//
//    // Handle screen resume
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_RESUME) {
//                if (userId.isNotBlank()) {
//                    viewModel.onEvent(AllProductsEvent.RefreshProducts(userId))
//                }
//            }
//        }
//
//        lifecycleOwner.lifecycle.addObserver(observer)
//
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = onCreateProductClick,
//                containerColor = MaterialTheme.colorScheme.primary
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add Product",
//                    tint = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//            if (state.isLoading) {
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
//
//            state.error?.let { error ->
//                Text(
//                    text = error,
//                    color = MaterialTheme.colorScheme.error,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
//
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(state.products) { product ->
//                    ProductCard(
//                        product = product,
//                        onDeleteClick = { productId ->
//                            viewModel.deleteProduct(productId, userId)
//                        },
//                        onEditClick = { onEditClick(product._id) },
//                        onProductClick = { onProductClick(product._id) }
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun ProductCard(
//    product: Product,
//    onDeleteClick: (String) -> Unit,
//    onEditClick: (String) -> Unit,
//    onProductClick: (String) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp)
//            .clickable { onProductClick(product._id) }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = product.title,
//                    style = MaterialTheme.typography.headlineSmall
//                )
//                Row {
//                    IconButton(onClick = { onEditClick(product._id) }) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = "Edit product"
//                        )
//                    }
//                    IconButton(onClick = { onDeleteClick(product._id) }) {
//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = "Delete product"
//                        )
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = product.description,
//                style = MaterialTheme.typography.bodyMedium
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Type: ${product.carType ?: "N/A"}",
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//    }
//}




@Composable
fun AllProductsScreen(
    viewModel: AllProductsViewModel = koinViewModel(),
    userId: String,
    onProductClick: (String) -> Unit,
    onCreateProductClick: () -> Unit,
    onEditClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(userId) {
        if (userId.isNotBlank()) {
            viewModel.onEvent(AllProductsEvent.LoadProducts(userId))
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (userId.isNotBlank()) {
                    viewModel.onEvent(AllProductsEvent.RefreshProducts(userId))
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Cars",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateProductClick,
                containerColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Car",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                        text = state.error ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.products.isEmpty() -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No cars added yet",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(state.products) { product ->
                            ProductCard(
                                product = product,
                                onDeleteClick = { productId ->
                                    viewModel.deleteProduct(productId, userId)
                                },
                                onEditClick = { onEditClick(product._id) },
                                onProductClick = { onProductClick(product._id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun ProductCard(
//    product: Product,
//    onDeleteClick: (String) -> Unit,
//    onEditClick: (String) -> Unit,
//    onProductClick: (String) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onProductClick(product._id) },
//        elevation = CardDefaults.cardElevation(4.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column {
//                    Text(
//                        text = product.title,
//                        style = MaterialTheme.typography.titleLarge.copy(
//                            fontWeight = FontWeight.Bold
//                        )
//                    )
//                    Text(
//                        text = product.company,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//                    )
//                }
//                Row(
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    IconButton(
//                        onClick = { onEditClick(product._id) },
//                        colors = IconButtonDefaults.iconButtonColors(
//                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//                        )
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = "Edit car",
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
//                    IconButton(
//                        onClick = { onDeleteClick(product._id) },
//                        colors = IconButtonDefaults.iconButtonColors(
//                            containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
//                        )
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = "Delete car",
//                            tint = MaterialTheme.colorScheme.error
//                        )
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = product.description,
//                style = MaterialTheme.typography.bodyMedium,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                AssistChip(
//                    onClick = { },
//                    label = { Text(product.carType ?: "N/A") },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.ShoppingCart,
//                            contentDescription = null,
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }
//                )
//                if (!product.dealer.isNullOrBlank()) {
//                    AssistChip(
//                        onClick = { },
//                        label = { Text(product.dealer) },
//                        leadingIcon = {
//                            Icon(
//                                imageVector = Icons.Default.AccountBox,
//                                contentDescription = null,
//                                modifier = Modifier.size(16.dp)
//                            )
//                        }
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun ProductCard(
    product: Product,
    onDeleteClick: (String) -> Unit,
    onEditClick: (String) -> Unit,
    onProductClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onProductClick(product._id) },
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
            // Header with Title and Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Title Section
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.TimeToLeave,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Title:",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 28.dp)
                    )
                }

                // Action Buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(
                        onClick = { onEditClick(product._id) },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                        ),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit car",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    IconButton(
                        onClick = { onDeleteClick(product._id) },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
                        ),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete car",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider()
            Spacer(modifier = Modifier.height(12.dp))

            // Company Section
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Company:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = product.company,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Car Type Section
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocalShipping,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Car Type:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = product.carType ?: "N/A",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Dealer Section
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Store,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Dealer:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = product.dealer ?: "N/A",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (product.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                Spacer(modifier = Modifier.height(12.dp))

                // Description Section
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Description:",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 28.dp, top = 4.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}