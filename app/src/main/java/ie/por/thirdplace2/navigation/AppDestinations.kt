package ie.por.thirdplace2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.outlined.Login
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val icon: ImageVector
    val label: String
    val route: String
}


object About : AppDestination {
    override val icon = Icons.Filled.Info
    override val label = "About"
    override val route = "about"
}

object Add : AppDestination {
    override val icon = Icons.Filled.AddCircle
    override val label = "Add"
    override val route = "add"
}

object List : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.List
    override val label = "List"
    override val route = "list"
}

object Map : AppDestination {
    override val icon = Icons.Filled.Public
    override val label = "Map"
    override val route = "map"
}

object Login : AppDestination {
    override val icon = Icons.AutoMirrored.Outlined.Login
    override val label = "Login"
    override val route = "login"
}

object Signup : AppDestination {
    override val icon = Icons.Filled.PersonAdd
    override val label = "Signup"
    override val route = "signup"
}

object Profile : AppDestination {
    override val icon = Icons.Default.Person
    override val label = "Profile"
    override val route = "profile"
}


/*object Details : AppDestination {
    override val icon = Icons.Filled.Details
    override val label = "Details"
    const val idArg = "id"
    override val route = "details/{$idArg}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.IntType }
    )
}*/

val bottomAppBarDestinations = listOf(Add, List, Map, About, Profile)
val userSignedOutDestinations = listOf(Login, Signup)
val allDestinations = listOf(Add, List, Map, About, Login, Signup, Profile)


/*Icon list:
        Search
        Home
        Menu
        Close
        Settings
        Favorite
        Add
        Delete
        Logout
        Star
        Check Box
        Check Box Outline Blank
        Radio Button Unchecked
        Radio Button Checked
        Info
        Public (globe)
        Person
        Coffee
        Forum
        Restaurant
        Sports Volleyball
        Communities
        Local Library
        Nature People
        Do Not Disturb
        Wc
        Charging Station
        Card Membership
        House
        Laptop Mac
        Construction
        Lock Open Right
        */


