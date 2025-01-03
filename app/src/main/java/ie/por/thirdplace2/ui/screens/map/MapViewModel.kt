package ie.por.thirdplace2.ui.screens.map

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.location.LocationService
import ie.por.thirdplace2.ui.theme.ColorPrimary
import ie.por.thirdplace2.ui.theme.ColorSecondary
import ie.por.thirdplace2.ui.theme.ColorSurface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationTracker: LocationService
) : ViewModel() {

    private val _currentLatLng = MutableStateFlow(LatLng(0.0, 0.0))
    val currentLatLng: StateFlow<LatLng> get() = _currentLatLng

    private val _hasPermissions = MutableStateFlow(false)
    val hasPermissions: StateFlow<Boolean> get() = _hasPermissions

    fun setPermissions(permissions: Boolean) {
        _hasPermissions.value = permissions
    }

    private fun setCurrentLatLng(latLng: LatLng) {
        _currentLatLng.value = latLng
    }

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

    fun getLocationUpdates() {
        viewModelScope.launch(Dispatchers.IO) {
            locationTracker.getLocationFlow()
                .collect {
                    it?.let { location ->
                        setCurrentLatLng(
                            LatLng(location.latitude,
                                location.longitude)
                        )
                    }
                }
        }
    }
}