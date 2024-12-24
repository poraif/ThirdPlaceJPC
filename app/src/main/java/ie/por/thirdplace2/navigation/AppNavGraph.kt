package ie.por.thirdplace2.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.por.thirdplace2.ui.screens.about.AboutScreen
import ie.por.thirdplace2.ui.screens.add.AddScreen
import ie.por.thirdplace2.ui.screens.details.DetailsScreen
import ie.por.thirdplace2.ui.screens.home.HomeScreen
import ie.por.thirdplace2.ui.screens.list.ListScreen
import ie.por.thirdplace2.ui.screens.login.LoginScreen
import ie.por.thirdplace2.ui.screens.map.MapScreen
import ie.por.thirdplace2.ui.screens.profile.ProfileScreen
import ie.por.thirdplace2.ui.screens.register.RegisterScreen


@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
    paddingValues: PaddingValues,
    permissions: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        //Add Screen
        composable(route = Add.route) {
            AddScreen(modifier = modifier)
        }

        //Home screen
        composable(route = Home.route) {
            HomeScreen(modifier = modifier)
        }

        //List Screen
        composable(route = List.route) {
            ListScreen(modifier = modifier,
                onClickThirdPlaceDetails = {
                        thirdPlaceId : Int ->
                    navController.navigateToThirdPlaceEdit(thirdPlaceId)
                },
            )
        }

        //Map screen
        composable(route = Map.route) {
            MapScreen(permissions = permissions)
        }

        //About screen
        composable(route = About.route) {
            AboutScreen(modifier = modifier)
        }

        //Login screen
        composable(route = Login.route) {
            LoginScreen(
                navController = navController,
                onLogin = { navController.popBackStack() }
            )
        }

        //Register screen
        composable(route = Register.route) {
            RegisterScreen(
                navController = navController,
                onRegister = { navController.popBackStack() }
            )
        }

        //Profile screen
        composable(route = Profile.route) {
            ProfileScreen(
                onSignOut = {
                    navController.popBackStack()
                    navController.navigate(Login.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                },
            )
        }

        //Details screen
        composable(
            route = Details.route,
            arguments = Details.arguments
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }

    }
}

private fun NavController.navigateToThirdPlaceEdit(thirdPlaceId: Int) {
    this.navigate("edit/$thirdPlaceId")
}
