package ie.por.thirdplace2.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ThirdPlaceModel(
                    @PrimaryKey(autoGenerate = true)
                    var id: String = "abc",
                    var title: String = "",
                    var description: String = "",
                    var amenities: List<String> = emptyList(),
                    var type: String = "",
                    var image: Uri = Uri.EMPTY,
                    var lat : Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f
            )


val fakePlaces = List(30) { i ->
    ThirdPlaceModel(
        id = "12345a$i",
        "Place $i",
        type = "Type $i",
        amenities = listOf("Amenity $i", "Amenity $i"),
        description = "Description $i",
        image = Uri.parse("https://picsum.photos/200/300?random=$i"),
        lat = 37.7749 + i * 0.01,
        lng = -122.4194 + i * 0.01,
        zoom = 10f
    )
}



    @Entity
    data class Location(
        @PrimaryKey(autoGenerate = true)
        var lat: Double = 0.0,
        var lng: Double = 0.0,
        var zoom: Float = 0f
    )
