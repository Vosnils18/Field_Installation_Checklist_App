package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.viewmodel.CabinetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CabinetSpecsScreen(navController: NavController, projectId: Long, cabinetId: Long?, viewModel: CabinetViewModel) {
    var currentStep by remember { mutableIntStateOf(1) }
    val totalSteps = 8

    var voltage by remember { mutableStateOf("") }
    var voltageOther by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("") }
    var frequencyOther by remember { mutableStateOf("") }
    var current by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var materialOther by remember { mutableStateOf("") }
    var colour by remember { mutableStateOf("") }
    var colourOther by remember { mutableStateOf("") }
    var ingressProtection by remember { mutableStateOf("") }
    var ingressProtectionOther by remember { mutableStateOf("") }
    var siteConditions by remember { mutableStateOf("") }
    var siteConditionsOther by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var depth by remember { mutableStateOf("") }
    var mounting by remember { mutableStateOf("") }
    var mountingOther by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var freeSpaceRight by remember { mutableStateOf("") }
    var freeSpaceLeft by remember { mutableStateOf("") }
    var freeSpaceTop by remember { mutableStateOf("") }
    var freeSpaceBottom by remember { mutableStateOf("") }
    var numberOfDoors by remember { mutableStateOf("") }
    var doorSwingDirection by remember { mutableStateOf("") }
    var intakeGrillePosition by remember { mutableStateOf("") }
    var exhaustGrillePosition by remember { mutableStateOf("") }
    var cableEntryPosition by remember { mutableStateOf("") }
    var cableEntryType by remember { mutableStateOf("") }
    var cableEntryTypeOther by remember { mutableStateOf("") }
    var cableGlandMaterial by remember { mutableStateOf("") }
    var cabinetAccessories by remember { mutableStateOf("") }
    var cabinetAccessoriesOther by remember { mutableStateOf("") }
    var finalComments by remember { mutableStateOf("") }
    var photosTakenOutside by remember { mutableStateOf(false) }
    var photosTakenInside by remember { mutableStateOf(false) }
    var photoOfCabinetSent by remember { mutableStateOf(false) }

    LaunchedEffect(cabinetId) {
        cabinetId?.let { id ->
            viewModel.getCabinet(id).collect { cabinet ->
                cabinet?.let {
                    voltage = it.voltage
                    frequency = if (it.frequency > 0) "${it.frequency.toInt()} Hz" else ""
                    current = if (it.current > 0) it.current.toString() else ""
                    material = it.material
                    colour = it.colour
                    ingressProtection = it.ingressProtection
                    siteConditions = it.siteConditions
                    width = if (it.width > 0) it.width.toInt().toString() else ""
                    height = if (it.height > 0) it.height.toInt().toString() else ""
                    depth = if (it.depth > 0) it.depth.toInt().toString() else ""
                    mounting = it.mounting
                    location = it.location
                    freeSpaceRight = if (it.freeSpaceRight > 0) it.freeSpaceRight.toInt().toString() else ""
                    freeSpaceLeft = if (it.freeSpaceLeft > 0) it.freeSpaceLeft.toInt().toString() else ""
                    freeSpaceTop = if (it.freeSpaceTop > 0) it.freeSpaceTop.toInt().toString() else ""
                    freeSpaceBottom = if (it.freeSpaceBottom > 0) it.freeSpaceBottom.toInt().toString() else ""
                    numberOfDoors = if (it.numberOfDoors > 0) it.numberOfDoors.toString() else ""
                    doorSwingDirection = it.doorSwingDirection
                    intakeGrillePosition = it.intakeGrillePosition
                    exhaustGrillePosition = it.exhaustGrillePosition
                    cableEntryPosition = it.cableEntryPosition
                    cableEntryType = it.cableEntryType.split(" - ").firstOrNull() ?: ""
                    cabinetAccessories = it.cabinetAccessories
                    finalComments = it.finalComments
                    photosTakenOutside = it.photosTakenOutside
                    photosTakenInside = it.photosTakenInside
                    photoOfCabinetSent = it.photoOfCabinetSent
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${if (cabinetId == null) "Add" else "Edit"} Cabinet - Step $currentStep/$totalSteps") },
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LinearProgressIndicator(
                progress = currentStep.toFloat() / totalSteps,
                modifier = Modifier.fillMaxWidth(),
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (currentStep) {
                    1 -> ElectricalSpecsStep(
                        voltage, voltageOther, frequency, frequencyOther, current,
                        { voltage = it }, { voltageOther = it }, { frequency = it }, { frequencyOther = it }, { current = it }
                    )
                    2 -> PhysicalSpecsStep(
                        material, materialOther, colour, colourOther, ingressProtection, ingressProtectionOther,
                        siteConditions, siteConditionsOther,
                        { material = it }, { materialOther = it }, { colour = it }, { colourOther = it },
                        { ingressProtection = it }, { ingressProtectionOther = it },
                        { siteConditions = it }, { siteConditionsOther = it }
                    )
                    3 -> DimensionsStep(width, height, depth, { width = it }, { height = it }, { depth = it })
                    4 -> MountingSpaceStep(
                        mounting, mountingOther, location, freeSpaceRight, freeSpaceLeft, freeSpaceTop, freeSpaceBottom,
                        { mounting = it }, { mountingOther = it }, { location = it },
                        { freeSpaceRight = it }, { freeSpaceLeft = it }, { freeSpaceTop = it }, { freeSpaceBottom = it }
                    )
                    5 -> CabinetFeaturesStep(
                        numberOfDoors, doorSwingDirection, intakeGrillePosition, exhaustGrillePosition,
                        { numberOfDoors = it }, { doorSwingDirection = it },
                        { intakeGrillePosition = it }, { exhaustGrillePosition = it }
                    )
                    6 -> CableEntryStep(
                        cableEntryPosition, cableEntryType, cableEntryTypeOther, cableGlandMaterial,
                        { cableEntryPosition = it }, { cableEntryType = it }, { cableEntryTypeOther = it }, { cableGlandMaterial = it }
                    )
                    7 -> AccessoriesNotesStep(
                        cabinetAccessories, cabinetAccessoriesOther, finalComments,
                        { cabinetAccessories = it }, { cabinetAccessoriesOther = it }, { finalComments = it }
                    )
                    8 -> PhotosStep(
                        photosTakenOutside, photosTakenInside, photoOfCabinetSent,
                        { photosTakenOutside = it }, { photosTakenInside = it }, { photoOfCabinetSent = it }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (currentStep > 1) {
                    OutlinedButton(onClick = { currentStep-- }) {
                        Text("Previous")
                    }
                } else {
                    OutlinedButton(onClick = { }, enabled = false) {
                        Text("Previous")
                    }
                }

                if (currentStep < totalSteps) {
                    Button(onClick = { currentStep++ }) {
                        Text("Next")
                    }
                } else {
                    Button(onClick = {
                        val finalVoltage = if (voltage == "Other") voltageOther else voltage
                        val finalFrequency = if (frequency == "Other") frequencyOther else frequency
                        val finalMaterial = if (material == "Other") materialOther else material
                        val finalColour = if (colour == "Other") colourOther else colour
                        val finalIngressProtection = if (ingressProtection == "Other") ingressProtectionOther else ingressProtection
                        val finalSiteConditions = if (siteConditions == "Other") siteConditionsOther else siteConditions
                        val finalMounting = if (mounting == "Other") mountingOther else mounting
                        val finalCableEntryType = if (cableEntryType == "Other") cableEntryTypeOther
                        else if (cableEntryType == "Cable Glands") "Cable Glands - $cableGlandMaterial"
                        else cableEntryType
                        val finalAccessories = if (cabinetAccessories == "Other") cabinetAccessoriesOther else cabinetAccessories

                        val cabinet = Cabinet(
                            projectId = projectId,
                            voltage = finalVoltage,
                            frequency = finalFrequency.replace(" Hz", "").toDoubleOrNull() ?: 0.0,
                            current = current.toDoubleOrNull() ?: 0.0,
                            siteConditions = finalSiteConditions,
                            material = finalMaterial,
                            colour = finalColour,
                            ingressProtection = finalIngressProtection,
                            mounting = finalMounting,
                            cabinetAccessories = finalAccessories,
                            cableEntryPosition = cableEntryPosition,
                            cableEntryType = finalCableEntryType,
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
                        if (cabinetId == null) {
                            viewModel.insertCabinet(cabinet)
                        } else {
                            viewModel.updateCabinet(cabinet.copy(id = cabinetId))
                        }
                        navController.popBackStack()
                    }) {
                        Text(if (cabinetId == null) "Save Cabinet" else "Update Cabinet")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectricalSpecsStep(
    voltage: String, voltageOther: String, frequency: String, frequencyOther: String, current: String,
    onVoltageChange: (String) -> Unit, onVoltageOtherChange: (String) -> Unit,
    onFrequencyChange: (String) -> Unit, onFrequencyOtherChange: (String) -> Unit,
    onCurrentChange: (String) -> Unit
) {
    Text("Electrical Specifications", style = MaterialTheme.typography.headlineSmall)

    DropdownField(
        label = "Voltage",
        options = listOf("240 VAC", "230 VAC", "3 X 400 VAC", "3 X 440 VAC", "Other"),
        selectedValue = voltage,
        onValueChange = onVoltageChange
    )

    if (voltage == "Other") {
        OutlinedTextField(
            value = voltageOther,
            onValueChange = onVoltageOtherChange,
            label = { Text("Specify Voltage") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    DropdownField(
        label = "Frequency",
        options = listOf("50 Hz", "60 Hz", "Other"),
        selectedValue = frequency,
        onValueChange = onFrequencyChange
    )

    if (frequency == "Other") {
        OutlinedTextField(
            value = frequencyOther,
            onValueChange = onFrequencyOtherChange,
            label = { Text("Specify Frequency") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    OutlinedTextField(
        value = current,
        onValueChange = onCurrentChange,
        label = { Text("Current (A)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhysicalSpecsStep(
    material: String, materialOther: String, colour: String, colourOther: String,
    ingressProtection: String, ingressProtectionOther: String,
    siteConditions: String, siteConditionsOther: String,
    onMaterialChange: (String) -> Unit, onMaterialOtherChange: (String) -> Unit,
    onColourChange: (String) -> Unit, onColourOtherChange: (String) -> Unit,
    onIngressProtectionChange: (String) -> Unit, onIngressProtectionOtherChange: (String) -> Unit,
    onSiteConditionsChange: (String) -> Unit, onSiteConditionsOtherChange: (String) -> Unit
) {
    Text("Physical Specifications", style = MaterialTheme.typography.headlineSmall)

    DropdownField(
        label = "Material",
        options = listOf("Plastic", "Aluminium", "Sheet steel", "Stainless Steel", "Other"),
        selectedValue = material,
        onValueChange = onMaterialChange
    )

    if (material == "Other") {
        OutlinedTextField(
            value = materialOther,
            onValueChange = onMaterialOtherChange,
            label = { Text("Specify Material") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    DropdownField(
        label = "Colour",
        options = listOf("RAL 7035 Light grey", "RAL 7032 Pebble Grey", "RAL 5015 Sky blue", "RAL 9003 Signal White", "Other"),
        selectedValue = colour,
        onValueChange = onColourChange
    )

    if (colour == "Other") {
        OutlinedTextField(
            value = colourOther,
            onValueChange = onColourOtherChange,
            label = { Text("Specify Colour") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    DropdownField(
        label = "Ingress Protection",
        options = listOf("IP 54", "IP 55", "IP 66", "IP 67", "Other"),
        selectedValue = ingressProtection,
        onValueChange = onIngressProtectionChange
    )

    if (ingressProtection == "Other") {
        OutlinedTextField(
            value = ingressProtectionOther,
            onValueChange = onIngressProtectionOtherChange,
            label = { Text("Specify Ingress Protection") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    DropdownField(
        label = "Site Conditions",
        options = listOf("Inside", "Outside", "non-ex", "ex", "H^2S", "Other"),
        selectedValue = siteConditions,
        onValueChange = onSiteConditionsChange
    )

    if (siteConditions == "Other") {
        OutlinedTextField(
            value = siteConditionsOther,
            onValueChange = onSiteConditionsOtherChange,
            label = { Text("Specify Site Conditions") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DimensionsStep(
    width: String, height: String, depth: String,
    onWidthChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onDepthChange: (String) -> Unit
) {
    Text("Dimensions", style = MaterialTheme.typography.headlineSmall)
    OutlinedTextField(
        value = width,
        onValueChange = onWidthChange,
        label = { Text("Width (mm)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    OutlinedTextField(
        value = height,
        onValueChange = onHeightChange,
        label = { Text("Height (mm)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    OutlinedTextField(
        value = depth,
        onValueChange = onDepthChange,
        label = { Text("Depth (mm)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MountingSpaceStep(
    mounting: String, mountingOther: String, location: String,
    freeSpaceRight: String, freeSpaceLeft: String, freeSpaceTop: String, freeSpaceBottom: String,
    onMountingChange: (String) -> Unit, onMountingOtherChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onFreeSpaceRightChange: (String) -> Unit,
    onFreeSpaceLeftChange: (String) -> Unit,
    onFreeSpaceTopChange: (String) -> Unit,
    onFreeSpaceBottomChange: (String) -> Unit
) {
    Text("Mounting & Space", style = MaterialTheme.typography.headlineSmall)

    DropdownField(
        label = "Mounting",
        options = listOf("Wall bracket", "Socket 100mm", "Socket 200mm", "Vibration Dampers", "Pole Clamp", "Other"),
        selectedValue = mounting,
        onValueChange = onMountingChange
    )

    if (mounting == "Other") {
        OutlinedTextField(
            value = mountingOther,
            onValueChange = onMountingOtherChange,
            label = { Text("Specify Mounting") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    OutlinedTextField(
        value = location,
        onValueChange = onLocationChange,
        label = { Text("Location") },
        modifier = Modifier.fillMaxWidth()
    )

    Text("Free Space Around Cabinet", style = MaterialTheme.typography.titleMedium)
    OutlinedTextField(
        value = freeSpaceRight,
        onValueChange = onFreeSpaceRightChange,
        label = { Text("Right (mm)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    OutlinedTextField(
        value = freeSpaceLeft,
        onValueChange = onFreeSpaceLeftChange,
        label = { Text("Left (mm)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    OutlinedTextField(
        value = freeSpaceTop,
        onValueChange = onFreeSpaceTopChange,
        label = { Text("Top (mm)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    OutlinedTextField(
        value = freeSpaceBottom,
        onValueChange = onFreeSpaceBottomChange,
        label = { Text("Bottom (mm)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun CabinetFeaturesStep(
    numberOfDoors: String, doorSwingDirection: String,
    intakeGrillePosition: String, exhaustGrillePosition: String,
    onNumberOfDoorsChange: (String) -> Unit,
    onDoorSwingDirectionChange: (String) -> Unit,
    onIntakeGrillePositionChange: (String) -> Unit,
    onExhaustGrillePositionChange: (String) -> Unit
) {
    Text("Cabinet Features", style = MaterialTheme.typography.headlineSmall)
    OutlinedTextField(
        value = numberOfDoors,
        onValueChange = onNumberOfDoorsChange,
        label = { Text("Number of Doors") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    OutlinedTextField(
        value = doorSwingDirection,
        onValueChange = onDoorSwingDirectionChange,
        label = { Text("Door Swing Direction") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = intakeGrillePosition,
        onValueChange = onIntakeGrillePositionChange,
        label = { Text("Intake Grille Position") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = exhaustGrillePosition,
        onValueChange = onExhaustGrillePositionChange,
        label = { Text("Exhaust Grille Position") },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CableEntryStep(
    cableEntryPosition: String, cableEntryType: String, cableEntryTypeOther: String, cableGlandMaterial: String,
    onCableEntryPositionChange: (String) -> Unit,
    onCableEntryTypeChange: (String) -> Unit,
    onCableEntryTypeOtherChange: (String) -> Unit,
    onCableGlandMaterialChange: (String) -> Unit
) {
    Text("Cable Entry", style = MaterialTheme.typography.headlineSmall)

    DropdownField(
        label = "Cable Entry Position",
        options = listOf("Top", "Bottom"),
        selectedValue = cableEntryPosition,
        onValueChange = onCableEntryPositionChange
    )

    DropdownField(
        label = "Cable Entry Type",
        options = listOf("Cable entry Grommets", "Cable Glands", "Open bottom", "IP 67", "Other"),
        selectedValue = cableEntryType,
        onValueChange = onCableEntryTypeChange
    )

    if (cableEntryType == "Cable Glands") {
        DropdownField(
            label = "Cable Gland Material/Type",
            options = listOf("Plastic", "Aluminium", "Brass", "Nickel plated", "Stainless steel",
                "Alternative clamping ring", "non-ex", "ex e", "ex d"),
            selectedValue = cableGlandMaterial,
            onValueChange = onCableGlandMaterialChange
        )
    }

    if (cableEntryType == "Other") {
        OutlinedTextField(
            value = cableEntryTypeOther,
            onValueChange = onCableEntryTypeOtherChange,
            label = { Text("Specify Cable Entry Type") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccessoriesNotesStep(
    cabinetAccessories: String, cabinetAccessoriesOther: String, finalComments: String,
    onCabinetAccessoriesChange: (String) -> Unit,
    onCabinetAccessoriesOtherChange: (String) -> Unit,
    onFinalCommentsChange: (String) -> Unit
) {
    Text("Accessories & Notes", style = MaterialTheme.typography.headlineSmall)

    DropdownField(
        label = "Cabinet Accessories",
        options = listOf("Wire Numbering", "Forced Ventilation", "Natural Ventilation", "Cabinet Heater",
            "Thermostat", "Hygrostat", "Cabinet Light", "Cable Connection Rail", "Lifting Lungs", "Door Stay", "Other"),
        selectedValue = cabinetAccessories,
        onValueChange = onCabinetAccessoriesChange
    )

    if (cabinetAccessories == "Other") {
        OutlinedTextField(
            value = cabinetAccessoriesOther,
            onValueChange = onCabinetAccessoriesOtherChange,
            label = { Text("Specify Accessories") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )
    }

    OutlinedTextField(
        value = finalComments,
        onValueChange = onFinalCommentsChange,
        label = { Text("Final Comments") },
        modifier = Modifier.fillMaxWidth(),
        minLines = 3
    )
}

@Composable
fun PhotosStep(
    photosTakenOutside: Boolean, photosTakenInside: Boolean, photoOfCabinetSent: Boolean,
    onPhotosTakenOutsideChange: (Boolean) -> Unit,
    onPhotosTakenInsideChange: (Boolean) -> Unit,
    onPhotoOfCabinetSentChange: (Boolean) -> Unit
) {
    Text("Photos Checklist", style = MaterialTheme.typography.headlineSmall)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = photosTakenOutside, onCheckedChange = onPhotosTakenOutsideChange)
        Text("Photos Taken Outside")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = photosTakenInside, onCheckedChange = onPhotosTakenInsideChange)
        Text("Photos Taken Inside")
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = photoOfCabinetSent, onCheckedChange = onPhotoOfCabinetSentChange)
        Text("Photo of Cabinet Sent")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
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