// navigation/NavGraph.kt
package com.example.foxbmsinstallationchecklist.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foxbmsinstallationchecklist.ui.screens.DimensionsScreen
import com.example.foxbmsinstallationchecklist.ui.screens.PhotoDocumentationScreen
import com.example.foxbmsinstallationchecklist.ui.screens.ProjectInfoScreen
import com.example.foxbmsinstallationchecklist.ui.screens.ReviewScreen
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel

sealed class Screen(val route: String) {
    object ProjectInfo : Screen("projectInfo")
    object Dimensions : Screen("dimensions")
    object PhotoDocumentation : Screen("photoDocumentation")
    object Review : Screen("review")
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: ChecklistViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProjectInfo.route
    ) {
        composable(
            route = Screen.ProjectInfo.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            ProjectInfoScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.Dimensions.route) }
            )
        }

        composable(
            route = Screen.Dimensions.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            DimensionsScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.PhotoDocumentation.route) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.PhotoDocumentation.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            PhotoDocumentationScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.Review.route) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Review.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            ReviewScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSubmit = { /* Handle submission */ }
            )
        }
    }
}
