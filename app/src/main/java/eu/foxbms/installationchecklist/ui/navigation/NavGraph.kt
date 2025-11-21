package eu.foxbms.installationchecklist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.foxbms.installationchecklist.ui.screens.AddProjectScreen
import eu.foxbms.installationchecklist.ui.screens.CabinetListScreen
import eu.foxbms.installationchecklist.ui.screens.CabinetSpecsScreen
import eu.foxbms.installationchecklist.ui.screens.ProjectListScreen
import eu.foxbms.installationchecklist.viewmodel.CabinetViewModel
import eu.foxbms.installationchecklist.viewmodel.PhotoViewModel
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
            route = "cabinet_list/{projectId}",
            arguments = listOf(navArgument("projectId") { type = NavType.LongType })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getLong("projectId") ?: 0L
            val viewModel: CabinetViewModel = hiltViewModel()
            CabinetListScreen(
                navController = navController,
                projectId = projectId,
                viewModel = viewModel
            )
        }

        composable(
            route = "cabinet_specs/{projectId}/{cabinetId}",
            arguments = listOf(
                navArgument("projectId") { type = NavType.LongType },
                navArgument("cabinetId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getLong("projectId") ?: 0L
            val cabinetIdStr = backStackEntry.arguments?.getString("cabinetId") ?: "new"
            val cabinetId = if (cabinetIdStr == "new") null else cabinetIdStr.toLongOrNull()
            val cabinetViewModel: CabinetViewModel = hiltViewModel()
            val photoViewModel: PhotoViewModel = hiltViewModel()
            CabinetSpecsScreen(
                navController = navController,
                projectId = projectId,
                cabinetId = cabinetId,
                viewModel = cabinetViewModel,
                photoViewModel = photoViewModel
            )
        }
    }
}