package com.example.foxbmsinstallationchecklist

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foxbmsinstallationchecklist.ui.theme.FoxBMSChecklistTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.compose.material3.ExperimentalMaterial3Api

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoxBMSChecklistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChecklistScreen()
                }
            }
        }
    }
}

@Composable
fun CameraPermissionRequest(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current
    val permission = Manifest.permission.CAMERA
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) onPermissionGranted()
        else onPermissionDenied()
    }
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted()
        } else {
            launcher.launch(permission)
        }
    }
}

@Composable
fun CameraPreview(
    onImageCaptured: (Uri) -> Unit,
    onError: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor: ExecutorService = remember { Executors.newSingleThreadExecutor() }
    val previewView = remember { PreviewView(context) }
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { previewView }
    )
    LaunchedEffect(Unit) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            try {
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val imageCapture = ImageCapture.Builder().build()
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageCapture
                )
                previewView.setOnClickListener {
                    val photoFile = File(
                        context.getExternalFilesDir(null),
                        SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
                            .format(System.currentTimeMillis()) + ".jpg"
                    )
                    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                    imageCapture.takePicture(
                        outputOptions,
                        cameraExecutor,
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                onImageCaptured(Uri.fromFile(photoFile))
                            }
                            override fun onError(exception: ImageCaptureException) {
                                onError(exception.message ?: "Unknown error")
                            }
                        }
                    )
                }
            } catch (exc: Exception) {
                onError(exc.message ?: "Unknown error")
            }
        }, ContextCompat.getMainExecutor(context))
    }
}

@Composable
fun PhotoPreview(photos: Map<String, String>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(photos.toList()) { (type, uri) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(type.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.labelMedium)
                AsyncImage(
                    model = uri,
                    contentDescription = "$type Photo",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChecklistScreen(viewModel: ChecklistViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var showCamera by remember { mutableStateOf(false) }
    var photoType by remember { mutableStateOf("") }
    var hasPermission by remember { mutableStateOf(false) }
    CameraPermissionRequest(
        onPermissionGranted = { hasPermission = true },
        onPermissionDenied = { /* Show a snackbar or dialog */ }
    )
    if (showCamera && hasPermission) {
        CameraPreview(
            onImageCaptured = { uri ->
                viewModel.addPhoto(photoType, uri.toString())
                showCamera = false
            },
            onError = { error -> /* Show a snackbar or dialog */ }
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    "Fox BMS Installation Checklist",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            item {
                CardStyling(
                    title = "Project Information",
                    content = {
                        ProjectInfoFields(uiState, viewModel)
                    }
                )
            }
            item {
                CardStyling(
                    title = "Dimensions and Specifications",
                    content = {
                        DimensionsFields(uiState, viewModel)
                    }
                )
            }
            item {
                CardStyling(
                    title = "Photo Documentation",
                    content = {
                        PhotoDocumentation(uiState, showCamera, photoType) { newType, newShowCamera ->
                            photoType = newType
                            showCamera = newShowCamera
                        }
                    }
                )
            }
            item {
                SubmitButton()
            }
        }
    }
}

@Composable
fun CardStyling(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            content()
        }
    }
}

@Composable
fun ProjectInfoFields(uiState: InstallationData, viewModel: ChecklistViewModel) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        CustomOutlinedTextField(
            value = uiState.projectInfo.name,
            onValueChange = { viewModel.updateProjectName(it) },
            label = "Project Name"
        )
        CustomOutlinedTextField(
            value = uiState.projectInfo.location,
            onValueChange = { viewModel.updateLocation(it) },
            label = "Location"
        )
        CustomOutlinedTextField(
            value = uiState.projectInfo.contactPerson,
            onValueChange = { viewModel.updateContactPerson(it) },
            label = "Contact Person"
        )
        CustomOutlinedTextField(
            value = uiState.projectInfo.contactNumber,
            onValueChange = { viewModel.updateContactNumber(it) },
            label = "Contact Number",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        CustomOutlinedTextField(
            value = uiState.projectInfo.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = "Email",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
    }
}

@Composable
fun DimensionsFields(uiState: InstallationData, viewModel: ChecklistViewModel) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        CustomOutlinedTextField(
            value = uiState.measurements.width,
            onValueChange = { viewModel.updateWidth(it) },
            label = "Width (cm)",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        CustomOutlinedTextField(
            value = uiState.measurements.height,
            onValueChange = { viewModel.updateHeight(it) },
            label = "Height (cm)",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        CustomOutlinedTextField(
            value = uiState.measurements.depth,
            onValueChange = { viewModel.updateDepth(it) },
            label = "Depth (cm)",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        CustomDropdownField(
            value = uiState.measurements.cabinetType,
            onValueChange = { viewModel.updateCabinetType(it) },
            label = "Cabinet Type",
            options = listOf("Wall-mounted", "Floor-standing", "Custom")
        )
        CustomDropdownField(
            value = uiState.measurements.cableEntryPosition,
            onValueChange = { viewModel.updateCableEntryPosition(it) },
            label = "Cable Entry Position",
            options = listOf("Top", "Bottom", "Side", "Rear")
        )
        CustomDropdownField(
            value = uiState.measurements.numberOfDoors,
            onValueChange = { viewModel.updateNumberOfDoors(it) },
            label = "Number of Doors",
            options = listOf("1", "2", "3", "4")
        )
        CustomDropdownField(
            value = uiState.measurements.numberOfShelves,
            onValueChange = { viewModel.updateNumberOfShelves(it) },
            label = "Number of Shelves",
            options = listOf("0", "1", "2", "3", "4")
        )
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun PhotoDocumentation(
    uiState: InstallationData,
    showCamera: Boolean,
    photoType: String,
    onPhotoTypeChange: (String, Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { onPhotoTypeChange("outside", true) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Outside Photo")
            }
            Button(
                onClick = { onPhotoTypeChange("inside", true) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Inside Photo")
            }
        }
        Button(
            onClick = { onPhotoTypeChange("cabinet", true) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cabinet Photo")
        }
        if (uiState.photos.isNotEmpty()) {
            Text("Captured Photos:", style = MaterialTheme.typography.bodyLarge)
            PhotoPreview(photos = uiState.photos)
        }
    }
}

@Composable
fun SubmitButton() {
    Button(
        onClick = { /* Handle submission */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Submit")
    }
}
