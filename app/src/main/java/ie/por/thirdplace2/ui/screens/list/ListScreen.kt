package ie.por.thirdplace2.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.por.thirdplace2.R
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.data.fakePlaces
import ie.por.thirdplace2.ui.components.general.Centre
import ie.por.thirdplace2.ui.components.list.PlaceCardList
import ie.por.thirdplace2.ui.components.list.PlaceListHeader
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@Composable
fun ListScreen(modifier: Modifier = Modifier,
               onClickThirdPlaceDetails: (Int) -> Unit,
                  listViewModel: ListViewModel = hiltViewModel()) {

    val thirdPlaces = listViewModel.uiThirdPlaces.collectAsState().value

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            PlaceListHeader()
            if(thirdPlaces.isEmpty())
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_list)
                    )
                }
            else
                PlaceCardList(
                    thirdPlaces = thirdPlaces,
                    onClickThirdPlaceDetails = onClickThirdPlaceDetails,
                    onDeletePlace = {
                            thirdPlace: ThirdPlaceModel ->
                        listViewModel.deleteThirdPlace(thirdPlace)
                    }
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    Thirdplace2Theme {
        PreviewListScreen( modifier = Modifier,
            thirdPlaces = fakePlaces.toMutableStateList()
        )
    }
}

@Composable
fun PreviewListScreen(modifier: Modifier = Modifier,
                        thirdPlaces: SnapshotStateList<ThirdPlaceModel>
) {

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            PlaceListHeader()
            if(thirdPlaces.isEmpty())
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_list)
                    )
                }
            else
                PlaceCardList (
                    thirdPlaces = thirdPlaces,
                    onDeletePlace = {},
                    onClickThirdPlaceDetails = { }
                )
        }
    }
}