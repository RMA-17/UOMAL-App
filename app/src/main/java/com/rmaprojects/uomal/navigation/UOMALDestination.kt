package com.rmaprojects.uomal.navigation

interface UOMALDestination {
    val route: String
    val routeName: String
    val createRoute: (String) -> String
}