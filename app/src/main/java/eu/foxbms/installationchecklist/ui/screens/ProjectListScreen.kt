package eu.foxbms.installationchecklist.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ProjectListScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Project List Screen")
        Button(onClick = { /* TODO: Navigate to project detail */ }) {
            Text("Add Project")
        }
    }
}
