package ie.por.thirdplace2.firebase.database

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import ie.por.thirdplace2.data.rules.Constants.THIRDPLACE_COLLECTION
import ie.por.thirdplace2.data.rules.Constants.USER_EMAIL
import ie.por.thirdplace2.firebase.services.AuthService
import ie.por.thirdplace2.firebase.services.FirestoreService
import ie.por.thirdplace2.firebase.services.StorageService
import ie.por.thirdplace2.firebase.services.ThirdPlace
import ie.por.thirdplace2.firebase.services.ThirdPlaces
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore,
                    private val storageService: StorageService
) : FirestoreService {

    override suspend fun getAll(email: String): ThirdPlaces {

        return firestore.collection(THIRDPLACE_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .dataObjects()
    }

    override suspend fun get(email: String,
                             thirdPlaceId: String): ThirdPlace? {
        return firestore.collection(THIRDPLACE_COLLECTION)
            .document(thirdPlaceId).get().await().toObject()
    }

    override suspend fun getImage(email: String, thirdPlaceId: String): Uri? {
        val thirdPlace = firestore.collection(THIRDPLACE_COLLECTION)
            .document(thirdPlaceId).get().await().toObject<ThirdPlace>()
        return Uri.parse(thirdPlace?.image)
    }

    override suspend fun insert(email: String,
                                thirdPlace: ThirdPlace, uri: Uri)
    {
        val thirdPlaceWithEmail = thirdPlace.copy(email = email, image = uploadCustomPhotoUri(uri).toString())

        firestore.collection(THIRDPLACE_COLLECTION)
            .add(thirdPlaceWithEmail)
            .await()
    }

    override suspend fun update(email: String,
                                thirdPlace: ThirdPlace, uri: Uri)
    {
        val updatedThirdPlace = thirdPlace.copy(email = email, image = updatePhotoUris(email, uri).toString())

        firestore.collection(THIRDPLACE_COLLECTION)
            .document(thirdPlace._id)
            .set(updatedThirdPlace).await()
    }

    override suspend fun delete(email: String,
                                thirdPlaceId: String)
    {
        firestore.collection(THIRDPLACE_COLLECTION)
            .document(thirdPlaceId)
            .delete().await()
    }

    private suspend fun uploadCustomPhotoUri(uri: Uri) : Uri {
        if (uri.toString().isNotEmpty()) {
            val urlTask = storageService.uploadFile(uri = uri, "images")
            val url = urlTask.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.e("task not successful: ${task.exception}")
                }
            }.await()
            return url
        }
        return Uri.EMPTY
    }

    private suspend fun updatePhotoUris(email: String, uri: Uri) {

        firestore.collection(THIRDPLACE_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Timber.i("FSR Updating ID ${document.id}")
                    firestore.collection(THIRDPLACE_COLLECTION)
                        .document(document.id)
                        .update("image", uri.toString())
                }
            }
            .addOnFailureListener { exception ->
                Timber.i("Error $exception")
            }
    }

}