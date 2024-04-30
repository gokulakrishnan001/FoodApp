package com.example.foodapp.screens

interface Destinations {
    val route: String
}

object OnBoard : Destinations {
    override val route = "OnBoard"
}
object Home : Destinations {
    override val route = "Home"
}

object  Profile: Destinations {
    override val route = "Profile"
}