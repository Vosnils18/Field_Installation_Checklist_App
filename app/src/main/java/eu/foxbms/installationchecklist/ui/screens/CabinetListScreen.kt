package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.viewmodel.CabinetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CabinetListScreen(
    navController: NavController,
    projectId: Long,
    viewModel: CabinetViewModel
) {
    val cabinets by viewModel.getCabinetsForProject(projectId).collectAsState(initial = emptyList())
    var cabinetToDelete by remember { mutableStateOf<Cabinet?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cabinets") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("cabinet_specs/$projectId/new") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Cabinet")
            }
        }
    ) { paddingValues ->
        if (cabinets.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No cabinets yet. Tap + to add one.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cabinets) { cabinet ->
                    CabinetCard(
                        cabinet = cabinet,
                        onClick = { navController.navigate("cabinet_specs/$projectId/${cabinet.id}") },
                        onDeleteClick = { cabinetToDelete = cabinet }
                    )
                }
            }
        }
    }

    if (cabinetToDelete != null) {
        AlertDialog(
            onDismissRequest = { cabinetToDelete = null },
            title = { Text("Delete Cabinet") },
            text = { Text("Are you sure you want to delete Cabinet #${cabinetToDelete?.id}? This cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        cabinetToDelete?.let { viewModel.deleteCabinet(it.id) }
                        cabinetToDelete = null
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { cabinetToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CabinetCard(
    cabinet: Cabinet,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Cabinet #${cabinet.id}",
                    style = MaterialTheme.typography.titleMedium
                )
                if (cabinet.voltage.isNotEmpty()) {
                    Text("Voltage: ${cabinet.voltage}", style = MaterialTheme.typography.bodyMedium)
                }
                if (cabinet.material.isNotEmpty()) {
                    Text("Material: ${cabinet.material}", style = MaterialTheme.typography.bodyMedium)
                }
                if (cabinet.location.isNotEmpty()) {
                    Text("Location: ${cabinet.location}", style = MaterialTheme.typography.bodyMedium)
                }
            }

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Cabinet",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}