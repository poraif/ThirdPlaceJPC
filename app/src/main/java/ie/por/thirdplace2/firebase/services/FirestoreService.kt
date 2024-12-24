package ie.por.thirdplace2.firebase.services

import android.net.Uri
import ie.por.thirdplace2.data.ThirdPlaceModel
import kotlinx.coroutines.flow.Flow

typealias ThirdPlace = ThirdPlaceModel
typealias ThirdPlaces = Flow<List<ThirdPlace>>


interface FirestoreService {

    suspend fun getAll(email: String) : ThirdPlaces
    suspend fun get(email: String, thirdPlaceId: String) : ThirdPlace?
    suspend fun insert(email: String, thirdPlace: ThirdPlace, uri: Uri)
    suspend fun update(email: String, thirdPlace: ThirdPlace, uri: Uri)
    suspend fun delete(email: String, thirdPlaceId: String)
}