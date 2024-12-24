package ie.por.thirdplace2.ui.screens.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@Composable
fun MapScreen() {
    val uiSettings by remember { mutableStateOf(
        MapUiSettings(
        //myLocationButtonEnabled = true,
        compassEnabled = true,
        mapToolbarEnabled = true
    )
    ) }

    val properties by remember {
        mutableStateOf(
            MapProperties(
            mapType = MapType.NORMAL,
            //isMyLocationEnabled = true
        )
        )
    }

    val currentLocation = LatLng(52.245696, -7.139102)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 14f)
    }

    LaunchedEffect(currentLocation){
        cameraPositionState.animate(CameraUpdateFactory.newLatLng(currentLocation))
        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 14f)
    }

    Column{
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            properties = properties
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "SETU",
                snippet = "This is SETU"
            )

        }
    }}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    Thirdplace2Theme {
        MapScreen( )
    }
}