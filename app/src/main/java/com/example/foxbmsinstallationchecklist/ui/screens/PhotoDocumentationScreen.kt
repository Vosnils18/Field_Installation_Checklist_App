// ui/screens/PhotoDocumentationScreen.kt
package com.example.foxbmsinstallationchecklist.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.foxbmsinstallationchecklist.ui.theme.FoxPhotoPreview
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel
import java.io.File

@Composable
fun PhotoDocumentationScreen(
    viewModel: ChecklistViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var showCamera by remember { mutableStateOf(false) }
    var photoType by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Camera setup
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            // Photo was captured successfully
            showCamera = false
        }
    }

    fun takePhoto(type: String) {
        photoType = type
        val photoFile = createImageFile(context)
        val photoUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            photoFile
        )

        cameraLauncher.launch(photoUri)
        viewModel.addPhoto(type, photoFile.absolutePath)
    }

    BaseScreen(
        title = "Photo Documentation",
        onBack = onBack,
        onNext = onNext
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Capture photos of the installation",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { takePhoto("outside") },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Outside")
                }

                Button(
                    onClick = { takePhoto("inside") },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Inside")
                }
            }

            Button(
                onClick = { takePhoto("cabinet") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cabinet")
            }

            if (uiState.photos.isNotEmpty()) {
                Text(
                    "Captured Photos:",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 16.dp)
                )

                FoxPhotoPreview(photos = uiState.photos)
            }
        }
    }
}

private fun createImageFile(context: android.content.Context): File {
    val timeStamp = System.currentTimeMillis()
    val storageDir = context.getExternalFilesDir(null)
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    )
}
