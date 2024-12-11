package ie.por.thirdplace2.ui.components.add

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.por.thirdplace2.R

@Composable
fun AmenitiesCheckBoxGroup(modifier: Modifier = Modifier, onAmenitiesChange: (List<String>) -> Unit) {

    val amenitiesOptions = listOf(
        stringResource(R.string.amenity_free),
        stringResource(R.string.amenity_charging),
        stringResource(R.string.amenity_toilets),
        stringResource(R.string.amenity_shelter),
        stringResource(R.string.amenity_quiet),
        stringResource(R.string.amenity_membership),
        stringResource(R.string.amenity_laptop),
        stringResource(R.string.amenity_equipment),
        stringResource(R.string.amenity_alwaysOpen)
    )

    val amenities = remember { mutableStateOf(emptyList<String>()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        amenitiesOptions.forEach { amenity ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = amenities.value.contains(amenity),
                    onCheckedChange = { checked ->
                        val updatedList = if (checked) {
                            amenities.value + amenity
                        } else {
                            amenities.value - amenity
                        }
                        amenities.value = updatedList
                        onAmenitiesChange(updatedList)
                    }
                )
                Text(
                    text = amenity,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp))
            }
        }

    }
}

