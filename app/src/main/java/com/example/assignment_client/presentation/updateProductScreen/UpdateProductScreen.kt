package com.example.assignment_client.presentation.updateProductScreen
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
import org.koin.core.parameter.parametersOf


@Composable
fun UpdateProductScreen(
    productId: String,
    userId: String,
    viewModel: UpdateProductViewModel = koinViewModel(parameters = { parametersOf(productId, userId) }),
    onSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        viewModel.onEvent(UpdateProductEvent.ImagesSelected(uris))
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(UpdateProductEvent.LoadProduct)
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = state.title,
            onValueChange = { viewModel.onEvent(UpdateProductEvent.TitleChanged(it)) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.description,
            onValueChange = { viewModel.onEvent(UpdateProductEvent.DescriptionChanged(it)) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        TextField(
            value = state.tags.joinToString(","),
            onValueChange = { viewModel.onEvent(UpdateProductEvent.TagsChanged(it)) },
            label = { Text("Tags (comma-separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.company,
            onValueChange = { viewModel.onEvent(UpdateProductEvent.CompanyChanged(it)) },
            label = { Text("Company") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.carType,
            onValueChange = { viewModel.onEvent(UpdateProductEvent.CarTypeChanged(it)) },
            label = { Text("Car Type") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = state.dealer,
            onValueChange = { viewModel.onEvent(UpdateProductEvent.DealerChanged(it)) },
            label = { Text("Dealer") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Existing Images:", style = MaterialTheme.typography.titleMedium)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.existingImages) { image ->
                AsyncImage(
                    model = image.url,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Button(
            onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select New Images (${state.selectedImages.size} selected)")
        }

        if (state.selectedImages.isNotEmpty()) {
            Text("New Images:", style = MaterialTheme.typography.titleMedium)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.selectedImages) { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        Button(
            onClick = { viewModel.onEvent(UpdateProductEvent.SubmitClicked) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Update Product")
            }
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}