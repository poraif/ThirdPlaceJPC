package ie.por.thirdplace2.ui.screens.details

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import ie.por.thirdplace2.ui.components.details.DetailsHeader
import ie.por.thirdplace2.ui.components.details.ReadOnlyTextField
import ie.por.thirdplace2.ui.components.general.ShowImagePicker
import ie.por.thirdplace2.ui.components.general.ShowLoader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailsViewModel = hiltViewModel()
) {
    var thirdPlace = detailViewModel.thirdPlace.value
    val errorEmptyTitle = "Please enter a title"
    val errorShortTitle = "Please enter a title longer than 6 characters"
    val errorDescriptionLength = "Please enter a description longer than 20 characters"
    val errorEmptyType = "Please select a type of place from the options"

    var text by rememberSaveable { mutableStateOf("") }
    var image by rememberSaveable { mutableStateOf("") }
    var uri by rememberSaveable { mutableStateOf(Uri.EMPTY) }
    var onTitleChanged by rememberSaveable { mutableStateOf(false) }
    var onDescriptionChanged by rememberSaveable { mutableStateOf(false) }
    var onTypeChanged by rememberSaveable { mutableStateOf(false) }
    var onAmenitiesChanged by rememberSaveable { mutableStateOf(false) }
    var onImageChanged by rememberSaveable { mutableStateOf(false) }

    var isEmptyError by rememberSaveable { mutableStateOf(false) }
    var titleShortError by rememberSaveable { mutableStateOf(false) }
    var descriptionShortError by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val isError = detailViewModel.isErr.value
    val error = detailViewModel.error.value
    val isLoading = detailViewModel.isLoading.value

    if(isLoading) ShowLoader("Retrieving details of Third Place")

    fun validate(text: String) {
        isEmptyError = text.isEmpty()
        titleShortError = text.length < 6
        descriptionShortError = text.length < 20
        onTitleChanged = !(isEmptyError || titleShortError)
        onDescriptionChanged = !(descriptionShortError)
        onTypeChanged = !(isEmptyError)
    }

    if(isError)
        Toast.makeText(context,"Error fetching details",
            Toast.LENGTH_SHORT).show()
    if(!isError && !isLoading)
        Column(modifier = modifier.padding(
            start = 24.dp,
            end = 24.dp,
        ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            DetailsHeader()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(
                    start = 10.dp,
                    end = 10.dp,
                ),
            )
            {
                //Title
                text = thirdPlace.title
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = {
                        text = it
                        validate(text)
                        thirdPlace.title = text
                    },
                    maxLines = 2,
                    label = { Text(text = "title") },
                    isError = isEmptyError || titleShortError,
                    supportingText = {
                        if (isEmptyError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = errorEmptyTitle,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        else
                            if (titleShortError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = errorShortTitle,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                    },
                    trailingIcon = {
                        if (isEmptyError || titleShortError)
                            Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        else
                            Icon(
                                Icons.Default.Edit, contentDescription = "add/edit",
                                tint = Color.Black
                            )
                    },
                    keyboardActions = KeyboardActions { validate(text) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    )
                )


                //Description
                text = thirdPlace.description
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = {
                        text = it
                        validate(text)
                        thirdPlace.description = text
                    },
                    maxLines = 4,
                    label = { Text(text = "description") },
                    isError = descriptionShortError,
                    supportingText = {
                            if (descriptionShortError) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = errorDescriptionLength,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                    },
                    trailingIcon = {
                        if (descriptionShortError)
                            Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                        else
                            Icon(
                                Icons.Default.Edit, contentDescription = "add/edit",
                                tint = Color.Black
                            )
                    },
                    keyboardActions = KeyboardActions { validate(text) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    )
                )

                //Type
                ReadOnlyTextField(value = thirdPlace.type,
                    label = "Third Place type")

                //Amenities
                ReadOnlyTextField(value = thirdPlace.amenities.toString(),
                    label = "Amenities")

                //Image
                image = thirdPlace.image
                ShowImagePicker(
                    onImageChanged = {
                        image = it.toString()
                    }
                )

                Spacer(modifier.height(height = 48.dp))

                Button(
                    onClick = {
                        detailViewModel.updateThirdPlace(thirdPlace, image.toUri())
                        onTitleChanged = false
                        onDescriptionChanged = false
                    },
                    elevation = ButtonDefaults.buttonElevation(20.dp),
                    enabled = onTitleChanged && onDescriptionChanged,
                ){
                    Icon(Icons.Default.Save, contentDescription = "Save")
                    Spacer(modifier.width(width = 8.dp))
                    Text(
                        text = "Save",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
}