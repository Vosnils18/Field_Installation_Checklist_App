package eu.foxbms.installationchecklist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.foxbms.installationchecklist.ui.screens.ProjectListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "project_list"
    ) {
        composable("project_list") {
            ProjectListScreen(navController = navController)
        }
    }
}
