package com.rmaprojects.uomal.navigation

object Home: UOMALDestination {
    override val route: String = "home"
    override val routeName: String = "Home"
    override val createRoute: (String) -> String = {""}
}

object Detail: UOMALDestination {
    override val route: String = "details/{animeId}"
    override val routeName: String = "Anime Detail"
    override val createRoute: (String) -> String = { animeId ->
        "details/$animeId"
    }
}