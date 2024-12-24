package ie.por.thirdplace2.data

import androidx.room.Entity
import com.google.firebase.firestore.DocumentId

@Entity
data class ThirdPlaceModel(
    @DocumentId val _id: String = "N/A",
    var title: String = "",
    var description: String = "",
    val amenities: List<String> = emptyList(),
    val type: String = "",
    var image: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    var email: String = "peadar@email.com"
            )


val fakePlaces = List(30) { i ->
    ThirdPlaceModel(
        "12345$i",
         "test $i",
        "Type $i",
        List(3) { "Amenity $it" },
        "Outdoors $i",
        "https://picsum.photos/200/$i",
        123.00 + (i*100),
        123.00 + (i*100),
        "peadar@email.com"
    )
}

