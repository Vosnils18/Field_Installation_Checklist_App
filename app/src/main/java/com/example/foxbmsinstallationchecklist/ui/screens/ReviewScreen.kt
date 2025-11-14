// ui/screens/ReviewScreen.kt
package com.example.foxbmsinstallationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foxbmsinstallationchecklist.ui.theme.FoxPhotoPreview
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel

@Composable
fun ReviewScreen(
    viewModel: ChecklistViewModel,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

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

            Button(
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Installation Checklist")
            }
        }
    }
}
