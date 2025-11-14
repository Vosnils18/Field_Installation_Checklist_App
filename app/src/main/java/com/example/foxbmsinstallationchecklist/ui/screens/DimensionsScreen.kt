// ui/screens/DimensionsScreen.kt
package com.example.foxbmsinstallationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.foxbmsinstallationchecklist.ui.theme.FoxTextField
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel

@Composable
fun DimensionsScreen(
    viewModel: ChecklistViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Validation state
    var isValid by remember { mutableStateOf(false) }

    // Check validation whenever state changes
    LaunchedEffect(uiState.measurements) {
        isValid = with(uiState.measurements) {
            width.isNotBlank() && height.isNotBlank() && depth.isNotBlank()
        }
    }

    BaseScreen(
        title = "Dimensions",
        onBack = onBack,
        onNext = { if (isValid) onNext() },
        showNext = isValid
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Enter dimensions",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            FoxTextField(
                value = uiState.measurements.width,
                onValueChange = { viewModel.updateWidth(it) },
                label = "Width (cm)",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.measurements.width.isBlank()
            )

            FoxTextField(
                value = uiState.measurements.height,
                onValueChange = { viewModel.updateHeight(it) },
                label = "Height (cm)",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.measurements.height.isBlank()
            )

            FoxTextField(
                value = uiState.measurements.depth,
                onValueChange = { viewModel.updateDepth(it) },
                label = "Depth (cm)",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.measurements.depth.isBlank()
            )
        }
    }
}
