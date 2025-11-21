package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.foxbms.installationchecklist.data.local.Project

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectItem(project: Project, onClick: () -> Unit, navController: NavController) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(project.name, style = MaterialTheme.typography.bodyLarge)
            Text(project.description, style = MaterialTheme.typography.bodyMedium)
            Text("Contact: ${project.mainContact}", style = MaterialTheme.typography.bodySmall)
            Button(
                onClick = { navController.navigate("cabinet_list/${project.id}") },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("View Cabinets")
            }
        }
    }
}