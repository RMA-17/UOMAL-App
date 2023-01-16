package com.rmaprojects.uomal.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                goToDetail = {
                    navController.navigate(Detail.route)
                }
            )
        }
        composable(Detail.route) {
            DetailScreen()
        }
    }
}

@Preview
@Composable
fun UOMALAppPreview() {
    UOMALApp()
}