package ie.por.thirdplace2.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ThirdPlaceModel(
                    @PrimaryKey(autoGenerate = true)
                    var id: String = "abc",
                    var title: String = "",
                    var description: String = "",
                    var amenities: List<String>,
                    var type: String = "",
                    var image: Uri = Uri.EMPTY,
                    var lat : Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f
            )


@Entity
data class Location(
                    @PrimaryKey(autoGenerate = true)
                    var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f)