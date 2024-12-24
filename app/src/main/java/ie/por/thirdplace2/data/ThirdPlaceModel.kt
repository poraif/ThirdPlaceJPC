package ie.por.thirdplace2.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

@Entity
data class ThirdPlaceModel(
    @PrimaryKey(autoGenerate = true)
                    val id: Int = 0,
    @DocumentId val _id: String = "N/A",
    var title: String = "",
    var description: String = "",
    val amenities: List<String> = emptyList(),
    val type: String = "",
    var image: String = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val zoom: Float = 0f,
    var email: String = "peadar@email.com"
            )


val fakePlaces = List(30) { i ->
    ThirdPlaceModel(
        12345+i,
         "12345 $i",
        "Type $i",
        "Description $i",
        listOf("Amenity $i", "Amenity $i"),
        "Outdoors $i",
        Uri.parse("https://picsum.photos/300/200"),
        37.7749 + i * 0.01,
        -122.4194 + i * 0.01,
        10f,
        "peadar@email.com"
    )
}



    @Entity
    data class Location(
        @PrimaryKey(autoGenerate = true)
        var lat: Double = 0.0,
        var lng: Double = 0.0,
        var zoom: Float = 0f
    )
