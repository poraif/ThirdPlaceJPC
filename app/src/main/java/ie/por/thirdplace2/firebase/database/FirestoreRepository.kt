package ie.por.thirdplace2.firebase.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import ie.por.thirdplace2.data.rules.Constants.THIRDPLACE_COLLECTION
import ie.por.thirdplace2.data.rules.Constants.USER_EMAIL
import ie.por.thirdplace2.firebase.services.AuthService
import ie.por.thirdplace2.firebase.services.FirestoreService
import ie.por.thirdplace2.firebase.services.ThirdPlace
import ie.por.thirdplace2.firebase.services.ThirdPlaces
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore
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

    override suspend fun insert(email: String,
                                thirdPlace: ThirdPlace)
    {
        val thirdPlaceWithEmail = thirdPlace.copy(email = email)

        firestore.collection(THIRDPLACE_COLLECTION)
            .add(thirdPlaceWithEmail)
            .await()
    }

    override suspend fun update(email: String,
                                thirdPlace: ThirdPlace)
    {
        val updatedThirdPlace = thirdPlace.copy(email = email)

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
}