package com.thebeastshop.beast.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.thebeastshop.beast.ui.wigdets.MainAppContent

object MainDestinations {
    const val HOME = "home"
    const val ADVERTISEMENT = "advertisement"
    const val VIDEO = "video"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME,
    showOnboardingInitially: Boolean = true
) {
    val onboardingComplete = remember(showOnboardingInitially) {
        mutableStateOf(!showOnboardingInitially)
    }

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME) {
            BackHandler {
                finishActivity()
            }
        }
        navigation(
            route = MainDestinations.HOME,
            startDestination = MainDestinations.VIDEO
        ) {

        }
        composable(
            MainDestinations.HOME,
        ) { backStackEntry: NavBackStackEntry ->
          //  val arguments = requireNotNull(backStackEntry.arguments)
        }
    }
}

class MainActions(navController: NavHostController) {
    val onboardingComplete: () -> Unit = {
        navController.popBackStack()
    }

    val advertisement = { newCourseId: Long, from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            navController.navigate(MainDestinations.ADVERTISEMENT)
        }
    }

    val video = { newCourseId: Long, from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            navController.navigate(MainDestinations.VIDEO)
        }
    }

    val home: (from: NavBackStackEntry) -> Unit = { from ->
        if (from.lifecycleIsResumed()) {
            navController.navigate(MainDestinations.HOME)
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED