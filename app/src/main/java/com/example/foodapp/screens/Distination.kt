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

object  ExpandFood:Destinations{
    override val route="Expand_food/{image}/{title}/{description}/{price}/{category}"
}

object Payment:Destinations{
    override val route="Payment"

}