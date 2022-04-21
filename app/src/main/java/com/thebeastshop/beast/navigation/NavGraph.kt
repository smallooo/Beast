package com.thebeastshop.beast.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
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
import com.thebeastshop.beast.theme.AppThemeState
import com.thebeastshop.beast.theme.SystemUiController
import com.thebeastshop.beast.ui.launchscreens.Launch
import com.thebeastshop.beast.ui.wigdets.BaseView
import com.thebeastshop.beast.ui.wigdets.MainAppContent

object MainDestinations {
    const val HOME = "home"
    const val ADVERTISEMENT = "advertisement"
    const val VIDEO = "video"
    const val LAUNCH = "launch"
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME,
    showOnboardingInitially: Boolean = true,
    systemUiController: SystemUiController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.LAUNCH) {
            BackHandler {
                finishActivity()
            }
            Launch(remember { mutableStateOf(AppThemeState()) })
        }

        navigation(
            route = MainDestinations.HOME,
            startDestination = MainDestinations.LAUNCH
        ) {


        }

        composable(
            MainDestinations.HOME,
        ) { backStackEntry: NavBackStackEntry ->
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            BaseView(appTheme.value, systemUiController) {
                MainAppContent(appTheme)
            }
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

    val launch: (from: NavBackStackEntry) -> Unit = { from ->
        if (from.lifecycleIsResumed()) {
            navController.navigate(MainDestinations.LAUNCH)
        }
    }

    private fun NavBackStackEntry.lifecycleIsResumed() =
        this.lifecycle.currentState == Lifecycle.State.RESUMED
}