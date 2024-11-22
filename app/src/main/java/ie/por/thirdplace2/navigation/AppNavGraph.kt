package ie.por.thirdplace2.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.por.thirdplace2.ui.screens.about.AboutScreen
import ie.por.thirdplace2.ui.screens.add.AddScreen
import ie.por.thirdplace2.ui.screens.list.ListScreen
import ie.por.thirdplace2.ui.screens.login.LoginScreen
import ie.por.thirdplace2.ui.screens.signup.SignupScreen
import ie.por.thirdplace2.ui.screens.profile.ProfileScreen
import ie.por.thirdplace2.ui.screens.map.MapScreen




@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        //Add Screen
        composable(route = Add.route) {
            AddScreen(modifier = modifier)
        }

        //List Screen
        composable(route = List.route) {
            AddScreen(modifier = modifier,
                onClickThirdPlaceEdit = {
                        thirdPlaceId : Int ->
                    navController.navigateToThirdPlaceEdit(thirdPlaceId)
                },
            )
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

        //Signup screen
        composable(route = Signup.route) {
            SignupScreen(
                navController = navController,
                onRegister = { navController.popBackStack() }
            )
        }

    }
}

private fun NavHostController.navigateToThirdPlaceEdit(thirdPlaceId: Int) {
    this.navigate("edit/$thirdPlaceId")
}

/*        composable(
            route = Details.route,
            arguments = Details.arguments
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }*/