package eu.foxbms.installationchecklist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.foxbms.installationchecklist.ui.screens.AddProjectScreen
import eu.foxbms.installationchecklist.ui.screens.ProjectListScreen
import eu.foxbms.installationchecklist.viewmodel.ProjectViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "project_list"
    ) {
        composable("project_list") {
            val viewModel: ProjectViewModel = hiltViewModel()
            ProjectListScreen(navController = navController, viewModel = viewModel)
        }
        composable("add_project") {
            val viewModel: ProjectViewModel = hiltViewModel()
            AddProjectScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = "cabinet_specs/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getInt("projectId") ?: 0
            val viewModel: CabinetViewModel = hiltViewModel()
            CabinetSpecsScreen(navController = navController, projectId = projectId, viewModel = viewModel)
        }
    }
}