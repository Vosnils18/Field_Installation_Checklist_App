// ui/screens/ReviewScreen.kt
package com.example.foxbmsinstallationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foxbmsinstallationchecklist.ui.theme.FoxButton
import com.example.foxbmsinstallationchecklist.ui.theme.FoxPhotoPreview
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel
import kotlinx.coroutines.launch

@Composable
fun ReviewScreen(
    viewModel: ChecklistViewModel,
    onBack: () -> Unit,
    onSubmit: () -> Unit,
    onOpenDBSettings: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var isSubmitting by remember { mutableStateOf(false) }
    var submitSuccess by remember { mutableStateOf(false) }
    var submitError by remember { mutableStateOf(false) }

    BaseScreen(
        title = "Review",
        onBack = onBack
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Project Info Section
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Project Information", style = MaterialTheme.typography.titleMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Name: ${uiState.projectInfo.name}")
                    Text("Location: ${uiState.projectInfo.location}")
                    Text("Contact: ${uiState.projectInfo.contactPerson}")
                    Text("Phone: ${uiState.projectInfo.contactNumber}")
                    Text("Email: ${uiState.projectInfo.email}")
                }
            }

            // Dimensions Section
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Dimensions", style = MaterialTheme.typography.titleMedium)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Width: ${uiState.measurements.width} cm")
                    Text("Height: ${uiState.measurements.height} cm")
                    Text("Depth: ${uiState.measurements.depth} cm")
                    Text("Cabinet Type: ${uiState.measurements.cabinetType}")
                    if (uiState.measurements.cableEntryPosition.isNotEmpty()) {
                        Text("Cable Entry: ${uiState.measurements.cableEntryPosition}")
                    }
                    if (uiState.measurements.numberOfDoors.isNotEmpty()) {
                        Text("Doors: ${uiState.measurements.numberOfDoors}")
                    }
                    if (uiState.measurements.numberOfShelves.isNotEmpty()) {
                        Text("Shelves: ${uiState.measurements.numberOfShelves}")
                    }
                }
            }

            // Photos Section
            if (uiState.photos.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Photos", style = MaterialTheme.typography.titleMedium)
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                        FoxPhotoPreview(photos = uiState.photos)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Database configuration check
            if (!viewModel.hasCredentials()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Database not configured",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Please set up database connection before submitting",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FoxButton(
                            onClick = onOpenDBSettings,
                            text = "Configure Database",
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            // Submission status indicators
            else if (isSubmitting) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            else if (submitSuccess) {
                Text(
                    "Data submitted successfully to NAS!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else if (submitError) {
                Text(
                    "Error submitting data. Please try again.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            // Submit button - only show if we have credentials
            if (viewModel.hasCredentials()) {
                FoxButton(
                    onClick = {
                        isSubmitting = true
                        submitError = false
                        submitSuccess = false

                        coroutineScope.launch {
                            viewModel.submitData { success ->
                                isSubmitting = false
                                if (success) {
                                    submitSuccess = true
                                    onSubmit()
                                } else {
                                    submitError = true
                                }
                            }
                        }
                    },
                    text = "Submit to NAS",
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isSubmitting
                )
            }
        }
    }
}
