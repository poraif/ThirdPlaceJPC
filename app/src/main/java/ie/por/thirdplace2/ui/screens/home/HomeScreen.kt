package ie.por.thirdplace2.ui.screens.home


import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ie.por.thirdplace2.navigation.List
import ie.por.thirdplace2.navigation.Login
import ie.por.thirdplace2.navigation.NavHostProvider
import ie.por.thirdplace2.navigation.allDestinations
import ie.por.thirdplace2.navigation.bottomAppBarDestinations
import ie.por.thirdplace2.navigation.userSignedOutDestinations
import ie.por.thirdplace2.ui.components.general.BottomAppBarProvider
import ie.por.thirdplace2.ui.components.general.TopAppBarProvider
import ie.por.thirdplace2.ui.screens.map.MapViewModel
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               homeViewModel: HomeViewModel = hiltViewModel(),
               mapViewModel: MapViewModel = hiltViewModel(),
               navController: NavHostController = rememberNavController(),
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentNavBackStackEntry?.destination
    val currentBottomScreen =
        allDestinations.find { it.route == currentDestination?.route } ?: Login

    var startScreen = currentBottomScreen
    val currentUser = homeViewModel.currentUser
    val isActiveSession = homeViewModel.isAuthenticated()
    val userEmail = if (isActiveSession) currentUser?.email else ""
    val userName = if (isActiveSession) currentUser?.displayName else ""
    val userDestinations = if (!isActiveSession)
        userSignedOutDestinations
    else
        bottomAppBarDestinations

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    if (isActiveSession) {
        startScreen = List
        LaunchedEffect(locationPermissions.allPermissionsGranted) {
            locationPermissions.launchMultiplePermissionRequest()
            if (locationPermissions.allPermissionsGranted) {
                mapViewModel.setPermissions(true)
                mapViewModel.getLocationUpdates()
            }
        }
    }

    Timber.i("HOME LAT/LNG PERMISSIONS ${mapViewModel.hasPermissions.collectAsState().value} ")

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBarProvider(
            navController = navController,
            currentScreen = currentBottomScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            email = userEmail!!,
            name = userName!!,
        ) { navController.navigateUp() }
        },
        content = { paddingValues ->
            NavHostProvider(
                modifier = modifier,
                navController = navController,
                startDestination = startScreen,
                paddingValues = paddingValues,
                permissions = mapViewModel
                    .hasPermissions
                    .collectAsState().value
            )
        },
        bottomBar = {
            BottomAppBarProvider(
                navController,
                currentScreen = currentBottomScreen,
                userDestinations
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    Thirdplace2Theme {
        HomeScreen(modifier = Modifier)
    }
}