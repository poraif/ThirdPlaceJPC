package ie.por.thirdplace2.ui.components.add

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.por.thirdplace2.R
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.ui.components.general.ShowLoader
import ie.por.thirdplace2.ui.screens.add.AddViewModel
import ie.por.thirdplace2.ui.screens.list.ListViewModel
import timber.log.Timber

@Composable

fun AddThirdPlaceButton(
    modifier: Modifier = Modifier,
    thirdPlace: ThirdPlaceModel,
    addViewModel: AddViewModel = hiltViewModel(),
    listViewModel: ListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val thirdPlaces = listViewModel.uiThirdPlaces.collectAsState().value
    val title = stringResource(R.string.hint_placeTitle)
    val description = ""
    val amenities = intArrayOf(0, 1)
    val type = ""
    val image = Uri.EMPTY
    val lat = 0.0
    val lng = 0.0
    val zoom = 0

    val isError = addViewModel.isErr.value
    val error = addViewModel.error.value
    val isLoading = addViewModel.isLoading.value

    if (isLoading) ShowLoader("Trying to add a place...")

    Row {
        Button(
            onClick = {
                addViewModel.insert(thirdPlace)
                Timber.i("Place added : $thirdPlace")
                Timber.i("List of places info : ${thirdPlaces.toList()}")
            },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add place")
            Spacer(modifier.width(width = 5.dp))
            Text(
                text = stringResource(R.string.button_addPlace),
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier.weight(1f))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                ) {
                    append(title)
                }
            })
    }

    if (isError) {
        Toast.makeText(
            context, "Unable to add a place at this Time",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        listViewModel.getThirdPlaces()
    }
}