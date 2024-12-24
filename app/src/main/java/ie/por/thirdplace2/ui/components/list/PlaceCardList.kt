package ie.por.thirdplace2.ui.components.list

import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.data.fakePlaces
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@Composable
internal fun PlaceCardList(
    thirdPlaces: List<ThirdPlaceModel>,
    modifier: Modifier = Modifier,
    onDeletePlace: (ThirdPlaceModel) -> Unit,
    onClickThirdPlaceDetails: (Int) -> Unit,
//    onRefreshList: () -> Unit,
) {
    LazyColumn {
        items(
            items = thirdPlaces,
            key = { thirdPlace -> thirdPlace._id  }
        ) { thirdPlace ->
            PlaceCard(
                title = thirdPlace.title,
                type = thirdPlace.type,
                amenities = thirdPlace.amenities,
                image = Uri.parse(thirdPlace.image),
                onClickDelete = { onDeletePlace(thirdPlace) },
                onClickThirdPlaceDetails = { onClickThirdPlaceDetails(thirdPlace.id) }
//                onRefreshList = { onRefreshList()
            )
        }
    }
}


@Preview(showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
fun PlaceCardListPreview() {
    Thirdplace2Theme {
        PlaceCardList(
            fakePlaces.toMutableStateList(),
            onDeletePlace = {},
            onClickThirdPlaceDetails = { }
//            onRefreshList = { }
        )
    }
}
