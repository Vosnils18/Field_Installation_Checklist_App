// navigation/NavGraph.kt
package com.example.foxbmsinstallationchecklist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foxbmsinstallationchecklist.ui.screens.DBSettingsScreen
import com.example.foxbmsinstallationchecklist.ui.screens.DimensionsScreen
import com.example.foxbmsinstallationchecklist.ui.screens.PhotoDocumentationScreen
import com.example.foxbmsinstallationchecklist.ui.screens.ProjectInfoScreen
import com.example.foxbmsinstallationchecklist.ui.screens.ReviewScreen
import com.example.foxbmsinstallationchecklist.utils.SecureCredentialsManager
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel

sealed class Screen(val route: String) {
    object ProjectInfo : Screen("projectInfo")
    object Dimensions : Screen("dimensions")
    object PhotoDocumentation : Screen("photoDocumentation")
    object Review : Screen("review")
    object DBSettings : Screen("dbSettings")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: ChecklistViewModel
) {
    val credentialsManager = SecureCredentialsManager(androidx.compose.ui.platform.LocalContext.current)

    NavHost(
        navController = navController,
        startDestination = Screen.ProjectInfo.route
    ) {
        composable(Screen.ProjectInfo.route) {
            ProjectInfoScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.Dimensions.route) }
            )
        }

        composable(Screen.Dimensions.route) {
            DimensionsScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.PhotoDocumentation.route) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.PhotoDocumentation.route) {
            PhotoDocumentationScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.Review.route) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Review.route) {
            ReviewScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSubmit = { /* Handle successful submission */ },
                onOpenDBSettings = { navController.navigate(Screen.DBSettings.route) }
            )
        }

        composable(Screen.DBSettings.route) {
            DBSettingsScreen(
                credentialsManager = credentialsManager,
                onSave = { navController.popBackStack() }
            )
        }
    }
}
