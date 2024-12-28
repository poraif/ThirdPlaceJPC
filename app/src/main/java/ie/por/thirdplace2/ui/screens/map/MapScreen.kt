package ie.por.thirdplace2.ui.screens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import ie.por.thirdplace2.ui.components.general.CustomMarker
import ie.por.thirdplace2.ui.screens.list.ListViewModel
import ie.por.thirdplace2.ui.theme.ColorPrimary
import ie.por.thirdplace2.ui.theme.ColorSecondary
import ie.por.thirdplace2.ui.theme.ColorSurface
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme
import timber.log.Timber

@Composable
fun MapScreen(
    mapViewModel: MapViewModel = hiltViewModel(),
    listViewModel: ListViewModel = hiltViewModel(),
    permissions: Boolean,
) {
    val uiSettings by remember { mutableStateOf(MapUiSettings(
        myLocationButtonEnabled = permissions,
        compassEnabled = true,
        mapToolbarEnabled = true
    )) }

    val properties by remember {
        mutableStateOf(MapProperties(
            mapType = MapType.NORMAL,
            isMyLocationEnabled = permissions,
        ))
    }

    val currentLocation = mapViewModel.currentLatLng.collectAsState().value
    val thirdPlaces = listViewModel.uiThirdPlaces.collectAsState().value

    Timber.i("MAP LAT/LNG PERMISSIONS $permissions ")

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 14f)
    }
    if (permissions) {
        LaunchedEffect(currentLocation) {
            mapViewModel.getLocationUpdates()
            cameraPositionState.animate(CameraUpdateFactory.newLatLng(currentLocation))
            cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 14f)
        }
    }

    Timber.i("MAP LAT/LNG COORDINATES $currentLocation ")

    fun getMarkerColor(type: String): Color {
        return when (type) {
            "Outdoors" -> ColorPrimary
            "Cultural" -> ColorSecondary
            "Food+drink" -> ColorSurface
            "Activity" -> Color.Yellow
            "Community space" -> Color.Cyan
            else -> Color.Gray
        }
    }

    Column {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            properties = properties
        ) {
            Marker(
                state = rememberMarkerState(position = currentLocation),
                title = "Current Location",
                snippet = "This is My Current Location"
            )
            thirdPlaces.forEach {
                val position = LatLng(it.latitude, it.longitude)
                val markerColor = getMarkerColor(it.type)
                MarkerComposable(
                    state = MarkerState(position = position),
                    title = it.title,
                    snippet = it.description
                ) {
                    CustomMarker(markerColor = markerColor)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    Thirdplace2Theme {
        MapScreen(permissions = true)
    }
}
