package ie.por.thirdplace2.data.api

import android.net.Uri
import ie.por.thirdplace2.data.ThirdPlaceModel

class ThirdPlaceWrapper {
    var title: String? = null
    var description: String? = null
    var amenities: List<String>? = null
    var type: String? = null
    var image: Uri? = null
    var lat : Double = 0.0
    var lng: Double = 0.0
    var zoom: Float = 0f
    var data: ThirdPlaceModel? = null
}