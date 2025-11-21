package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.ProjectEntity
import eu.foxbms.installationchecklist.viewmodel.ProjectViewModel

@Composable
fun ProjectListScreen(navController: NavController, viewModel: ProjectViewModel) {
    val projects by viewModel.projects.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Projects", style = MaterialTheme.typography.headlineMedium)

        Button(
            onClick = { navController.navigate("add_project") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Project")
        }

        LazyColumn {
            items(projects) { project ->
                ProjectItem(project = project, onClick = { /* TODO: Navigate to project details */ })
            }
        }
    }
}

@Composable
fun ProjectItem(project: ProjectEntity, onClick: () -> Unit) {
    TODO("Not yet implemented")
}
