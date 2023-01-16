package com.rmaprojects.uomal.navigation

object Home: UOMALDestination {
    override val route: String = "home"
    override val routeName: String = "Home"
}

object Detail: UOMALDestination {
    override val route: String = "details/{animeId}"
    override val routeName: String = "Anime Detail"
}

val uomalScreens = listOf(
    Home,
    Detail
)