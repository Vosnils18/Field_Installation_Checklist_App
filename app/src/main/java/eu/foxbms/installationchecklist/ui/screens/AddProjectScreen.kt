package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.ProjectEntity
import eu.foxbms.installationchecklist.viewmodel.ProjectViewModel
import java.util.*

@Composable
fun AddProjectScreen(navController: NavController, viewModel: ProjectViewModel) {
    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Create New Project", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = projectName,
            onValueChange = { projectName = it },
            label = { Text("Project Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = projectDescription,
            onValueChange = { projectDescription = it },
            label = { Text("Project Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val project = ProjectEntity(
                    name = projectName,
                    description = projectDescription,
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
