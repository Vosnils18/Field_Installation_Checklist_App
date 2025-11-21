package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.Project
import eu.foxbms.installationchecklist.viewmodel.ProjectViewModel
import java.util.*

@Composable
fun AddProjectScreen(navController: NavController, viewModel: ProjectViewModel) {
    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }
    var mainContact by remember { mutableStateOf("") } // New field for main contact

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Create New Project",
            style = MaterialTheme.typography.headlineMedium
        )

        // Project Name
        OutlinedTextField(
            value = projectName,
            onValueChange = { projectName = it },
            label = { Text("Project Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Project Description
        OutlinedTextField(
            value = projectDescription,
            onValueChange = { projectDescription = it },
            label = { Text("Project Description (Optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Main Contact (New Field)
        OutlinedTextField(
            value = mainContact,
            onValueChange = { mainContact = it },
            label = { Text("Main Contact") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Save Button
        Button(
            onClick = {
                val project = Project(
                    name = projectName,
                    description = projectDescription,
                    mainContact = mainContact, // Include main contact
                    createdAt = Calendar.getInstance().timeInMillis,
                    updatedAt = Calendar.getInstance().timeInMillis
                )
                viewModel.insertProject(project)
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save Project")
        }
    }
}
