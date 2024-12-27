package ie.por.thirdplace2.ui.screens.add

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.ui.components.add.AddPlaceHeader
import ie.por.thirdplace2.ui.components.add.AddThirdPlaceButton
import ie.por.thirdplace2.ui.components.add.AmenitiesCheckBoxGroup
import ie.por.thirdplace2.ui.components.add.DescriptionInput
import ie.por.thirdplace2.ui.components.add.TitleInput
import ie.por.thirdplace2.ui.components.add.TypeRadioButtonGroup
import ie.por.thirdplace2.ui.components.general.ShowImagePicker
import ie.por.thirdplace2.ui.screens.list.ListViewModel

@Composable
fun AddScreen(modifier: Modifier = Modifier, listViewModel: ListViewModel = hiltViewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var amenities by remember { mutableStateOf(emptyList<String>()) }
    var image by remember { mutableStateOf(Uri.EMPTY) }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddPlaceHeader()

        TitleInput(
            modifier = Modifier.fillMaxWidth(),
            onTitleChange = { title = it }
        )

        DescriptionInput(
            modifier = Modifier.fillMaxWidth(),
            onDescriptionChange = { description = it }
        )

        TypeRadioButtonGroup(
            modifier = Modifier.fillMaxWidth(),
            onPlaceTypeChange = { type = it }
        )

        AmenitiesCheckBoxGroup(
            onAmenitiesChange = { updatedAmenities ->
                amenities = updatedAmenities
            }
        )

        ShowImagePicker(
            onImageChanged = {
                image = it
            }
        )

        AddThirdPlaceButton(
            modifier = Modifier.fillMaxWidth(),
            thirdPlace = ThirdPlaceModel(
                title = title,
                description = description,
                type = type,
                amenities = amenities,
                image = image.toString()
            )
        )
    }
}