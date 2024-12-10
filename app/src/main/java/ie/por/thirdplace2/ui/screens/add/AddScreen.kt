package ie.por.thirdplace2.ui.screens.add

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel

import ie.por.thirdplace2.ui.components.add.AddImageButton
import ie.por.thirdplace2.ui.components.add.AddLocationButton
import ie.por.thirdplace2.ui.components.add.AddThirdPlaceButton
import ie.por.thirdplace2.ui.components.add.AddPlaceHeader
import ie.por.thirdplace2.ui.components.add.AmenitiesCheckBoxGroup
import ie.por.thirdplace2.ui.components.add.DescriptionInput
import ie.por.thirdplace2.ui.components.add.TitleInput
import ie.por.thirdplace2.ui.components.add.TypeRadioButtonGroup
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme
import ie.por.thirdplace2.ui.screens.list.ListViewModel


@Composable
fun AddScreen(modifier: Modifier = Modifier, listViewModel: ListViewModel = hiltViewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var amenities by remember { mutableStateOf(emptyList<String>()) }
    var image by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            AddPlaceHeader()
            Row(
                verticalAlignment = Alignment.CenterVertically,
            )
            TitleInput(
                modifier = modifier,
                onTitleChange = { title = it }
            )
            Spacer(modifier.weight(1f))
            DescriptionInput(
                modifier = modifier,
                onDescriptionChange = { description = it }
            )
            Spacer(modifier.weight(1f))
            TypeRadioButtonGroup(
                modifier = modifier,
                onPlaceTypeChange = { type = it }
            )
            Spacer(modifier.weight(1f))
            AmenitiesCheckBoxGroup(
                onAmenitiesChange = { updatedAmenities ->
                    amenities = updatedAmenities
                }
            )
            Spacer(modifier.weight(1f))
            AddImageButton(
                modifier = modifier,
                onImageChange = { image = it }
            )
            Spacer(modifier.weight(1f))
            AddLocationButton(
                modifier = modifier,
                onLocationChange = { location = it }
            )
            Spacer(modifier.weight(1f))
            AddThirdPlaceButton(
                modifier = modifier,
                thirdPlace = ThirdPlaceModel(title = title,
                                            description = description,
                                            type = type,
                                            amenities = amenities,
                                            image = image,
                                            location = location)
            )


        }
    }


}
