package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.viewmodel.CabinetViewModel
import java.util.*

@Composable
fun CabinetSpecsScreen(navController: NavController, projectId: Long, viewModel: CabinetViewModel) {
    // String fields
    var voltage by remember { mutableStateOf("") }
    var siteConditions by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var colour by remember { mutableStateOf("") }
    var ingressProtection by remember { mutableStateOf("") }
    var mounting by remember { mutableStateOf("") }
    var cabinetAccessories by remember { mutableStateOf("") }
    var cableEntryPosition by remember { mutableStateOf("") }
    var cableEntryType by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var finalComments by remember { mutableStateOf("") }
    var doorSwingDirection by remember { mutableStateOf("") }
    var intakeGrillePosition by remember { mutableStateOf("") }
    var exhaustGrillePosition by remember { mutableStateOf("") }

    // Double fields
    var frequency by remember { mutableStateOf("") }
    var current by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var depth by remember { mutableStateOf("") }
    var freeSpaceRight by remember { mutableStateOf("") }
    var freeSpaceLeft by remember { mutableStateOf("") }
    var freeSpaceTop by remember { mutableStateOf("") }
    var freeSpaceBottom by remember { mutableStateOf("") }

    // Int field
    var numberOfDoors by remember { mutableStateOf("") }

    // Boolean fields
    var photosTakenOutside by remember { mutableStateOf(false) }
    var photosTakenInside by remember { mutableStateOf(false) }
    var photoOfCabinetSent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Cabinet Specs", style = MaterialTheme.typography.headlineMedium)

        // String fields
        OutlinedTextField(
            value = voltage,
            onValueChange = { voltage = it },
            label = { Text("Voltage") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = siteConditions,
            onValueChange = { siteConditions = it },
            label = { Text("Site Conditions") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = material,
            onValueChange = { material = it },
            label = { Text("Material") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = colour,
            onValueChange = { colour = it },
            label = { Text("Colour") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = ingressProtection,
            onValueChange = { ingressProtection = it },
            label = { Text("Ingress Protection") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = mounting,
            onValueChange = { mounting = it },
            label = { Text("Mounting") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cabinetAccessories,
            onValueChange = { cabinetAccessories = it },
            label = { Text("Cabinet Accessories") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cableEntryPosition,
            onValueChange = { cableEntryPosition = it },
            label = { Text("Cable Entry Position (Onder/Boven)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cableEntryType,
            onValueChange = { cableEntryType = it },
            label = { Text("Cable Entry Type") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = finalComments,
            onValueChange = { finalComments = it },
            label = { Text("Final Comments") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = doorSwingDirection,
            onValueChange = { doorSwingDirection = it },
            label = { Text("Door Swing Direction") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = intakeGrillePosition,
            onValueChange = { intakeGrillePosition = it },
            label = { Text("Intake Grille Position") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = exhaustGrillePosition,
            onValueChange = { exhaustGrillePosition = it },
            label = { Text("Exhaust Grille Position") },
            modifier = Modifier.fillMaxWidth()
        )

        // Double fields
        OutlinedTextField(
            value = frequency,
            onValueChange = { frequency = it },
            label = { Text("Frequency") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = current,
            onValueChange = { current = it },
            label = { Text("Current") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = width,
            onValueChange = { width = it },
            label = { Text("Width (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = depth,
            onValueChange = { depth = it },
            label = { Text("Depth (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = freeSpaceRight,
            onValueChange = { freeSpaceRight = it },
            label = { Text("Free Space Right (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = freeSpaceLeft,
            onValueChange = { freeSpaceLeft = it },
            label = { Text("Free Space Left (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = freeSpaceTop,
            onValueChange = { freeSpaceTop = it },
            label = { Text("Free Space Top (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = freeSpaceBottom,
            onValueChange = { freeSpaceBottom = it },
            label = { Text("Free Space Bottom (mm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Int field
        OutlinedTextField(
            value = numberOfDoors,
            onValueChange = { numberOfDoors = it },
            label = { Text("Number of Doors") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Boolean fields (checkboxes)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = photosTakenOutside, onCheckedChange = { photosTakenOutside = it })
            Text("Photos Taken Outside")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = photosTakenInside, onCheckedChange = { photosTakenInside = it })
            Text("Photos Taken Inside")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = photoOfCabinetSent, onCheckedChange = { photoOfCabinetSent = it })
            Text("Photo of Cabinet Sent")
        }

        // Save button
        Button(
            onClick = {
                val cabinet = Cabinet(
                    projectId = projectId,
                    voltage = voltage,
                    frequency = frequency.toDoubleOrNull() ?: 0.0,
                    current = current.toDoubleOrNull() ?: 0.0,
                    siteConditions = siteConditions,
                    material = material,
                    colour = colour,
                    ingressProtection = ingressProtection,
                    mounting = mounting,
                    cabinetAccessories = cabinetAccessories,
                    cableEntryPosition = cableEntryPosition,
                    cableEntryType = cableEntryType,
                    location = location,
                    finalComments = finalComments,
                    width = width.toDoubleOrNull() ?: 0.0,
                    height = height.toDoubleOrNull() ?: 0.0,
                    depth = depth.toDoubleOrNull() ?: 0.0,
                    numberOfDoors = numberOfDoors.toIntOrNull() ?: 0,
                    doorSwingDirection = doorSwingDirection,
                    intakeGrillePosition = intakeGrillePosition,
                    exhaustGrillePosition = exhaustGrillePosition,
                    freeSpaceRight = freeSpaceRight.toDoubleOrNull() ?: 0.0,
                    freeSpaceLeft = freeSpaceLeft.toDoubleOrNull() ?: 0.0,
                    freeSpaceTop = freeSpaceTop.toDoubleOrNull() ?: 0.0,
                    freeSpaceBottom = freeSpaceBottom.toDoubleOrNull() ?: 0.0,
                    photosTakenOutside = photosTakenOutside,
                    photosTakenInside = photosTakenInside,
                    photoOfCabinetSent = photoOfCabinetSent
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
