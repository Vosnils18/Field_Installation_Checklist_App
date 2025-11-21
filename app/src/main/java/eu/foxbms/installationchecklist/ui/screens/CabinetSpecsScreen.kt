package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.CabinetEntity
import java.util.*

@Composable
fun CabinetSpecsScreen(navController: NavController, projectId: Int, viewModel: CabinetViewModel) {
    var cabinetName by remember { mutableStateOf("") }
    var cabinetWidth by remember { mutableStateOf("") }
    var cabinetHeight by remember { mutableStateOf("") }
    var cabinetDepth by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Cabinet Specs", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = cabinetName,
            onValueChange = { cabinetName = it },
            label = { Text("Cabinet Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cabinetWidth,
            onValueChange = { cabinetWidth = it },
            label = { Text("Width (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = cabinetHeight,
            onValueChange = { cabinetHeight = it },
            label = { Text("Height (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = cabinetDepth,
            onValueChange = { cabinetDepth = it },
            label = { Text("Depth (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val cabinet = CabinetEntity(
                    projectId = projectId,
                    name = cabinetName,
                    width = cabinetWidth.toDoubleOrNull() ?: 0.0,
                    height = cabinetHeight.toDoubleOrNull() ?: 0.0,
                    depth = cabinetDepth.toDoubleOrNull() ?: 0.0,
                    createdAt = Calendar.getInstance().timeInMillis,
                    updatedAt = Calendar.getInstance().timeInMillis
                )
                viewModel.insertCabinet(cabinet)
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save Cabinet")
        }
    }
}
