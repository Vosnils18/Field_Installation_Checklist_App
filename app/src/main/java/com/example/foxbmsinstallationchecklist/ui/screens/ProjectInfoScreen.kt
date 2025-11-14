// ui/screens/ProjectInfoScreen.kt
package com.example.foxbmsinstallationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foxbmsinstallationchecklist.ui.theme.FoxTextField
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel

@Composable
fun ProjectInfoScreen(
    viewModel: ChecklistViewModel,
    onNext: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var isValid by remember { mutableStateOf(false) }

    // Check if all required fields are filled
    LaunchedEffect(uiState.projectInfo) {
        isValid = with(uiState.projectInfo) {
            name.isNotBlank() && location.isNotBlank() && contactPerson.isNotBlank()
        }
    }

    BaseScreen(
        title = "Project Information",
        onNext = { if (isValid) onNext() },
        showNext = isValid
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Enter project details",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            FoxTextField(
                value = uiState.projectInfo.name,
                onValueChange = { viewModel.updateProjectName(it) },
                label = "Project Name",
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.projectInfo.name.isBlank()
            )

            FoxTextField(
                value = uiState.projectInfo.location,
                onValueChange = { viewModel.updateLocation(it) },
                label = "Location",
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.projectInfo.location.isBlank()
            )

            FoxTextField(
                value = uiState.projectInfo.contactPerson,
                onValueChange = { viewModel.updateContactPerson(it) },
                label = "Contact Person",
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.projectInfo.contactPerson.isBlank()
            )

            FoxTextField(
                value = uiState.projectInfo.contactNumber,
                onValueChange = { viewModel.updateContactNumber(it) },
                label = "Contact Number",
                keyboardType = KeyboardType.Phone,
                modifier = Modifier.fillMaxWidth()
            )

            FoxTextField(
                value = uiState.projectInfo.email,
                onValueChange = { viewModel.updateEmail(it) },
                label = "Email",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
