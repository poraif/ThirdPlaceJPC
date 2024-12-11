package ie.por.thirdplace2.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ThirdPlaceModel(
                    @PrimaryKey(autoGenerate = true)
                    val id: Int = 0,
                    val _id: String = "N/A",
                    val title: String = "",
                    val description: String = "",
                    val amenities: List<String> = emptyList(),
                    val type: String = "",
                    val image: Uri = Uri.parse("https://picsum.photos/300/200"),
                    val lat: Double = 0.0,
                    val lng: Double = 0.0,
                    val zoom: Float = 0f
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
        10f
    )
}



    @Entity
    data class Location(
        @PrimaryKey(autoGenerate = true)
        var lat: Double = 0.0,
        var lng: Double = 0.0,
        var zoom: Float = 0f
    )
