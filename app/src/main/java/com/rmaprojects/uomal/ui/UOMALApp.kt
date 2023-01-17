package com.rmaprojects.uomal.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rmaprojects.uomal.navigation.Detail
import com.rmaprojects.uomal.navigation.Home
import com.rmaprojects.uomal.ui.screen.detail.DetailScreen
import com.rmaprojects.uomal.ui.screen.home.HomeScreen

@Composable
fun UOMALApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
    ) {
        composable(Home.route) {
            HomeScreen(
                goToDetail = { animeId ->
                    navController.navigate(Detail.createRoute(animeId)) {
                        launchSingleTop = true

                        restoreState = true
                    }
                }
            )
        }
        composable(
            route = Detail.route,
            arguments = listOf(
                navArgument("animeId") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}

@Preview
@Composable
fun UOMALAppPreview() {
    UOMALApp()
}