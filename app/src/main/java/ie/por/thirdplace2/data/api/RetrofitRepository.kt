package ie.por.thirdplace2.data.api

import ie.por.thirdplace2.data.ThirdPlaceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject
constructor(private val serviceApi: ThirdPlaceService)  {

    suspend fun adminGetAll(): List<ThirdPlaceModel>
    {
        return withContext(Dispatchers.IO) {
            val thirdPlaces = serviceApi.admingetall()
            thirdPlaces.body() ?: emptyList()
        }
    }

    suspend fun getAll(email: String): List<ThirdPlaceModel>
    {
        return withContext(Dispatchers.IO) {
            val donations = serviceApi.getall(email)
            donations.body() ?: emptyList()
        }
    }

    suspend fun adminGet(id: String): List<ThirdPlaceModel>
    {
        return withContext(Dispatchers.IO) {
            val thirdPlace = serviceApi.adminget(id)
            thirdPlace.body() ?: emptyList()
        }
    }

    suspend fun get(email: String, id: String): List<ThirdPlaceModel>
    {
        return withContext(Dispatchers.IO) {
            val thirdPlace = serviceApi.get(email, id)
            thirdPlace.body() ?: emptyList()
        }
    }

    suspend fun insert(email: String, thirdPlace: ThirdPlaceModel) : ThirdPlaceWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(email, thirdPlace)
            wrapper
        }
    }

    suspend fun update(email: String, thirdPlace: ThirdPlaceModel) : ThirdPlaceWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(email, thirdPlace._id, thirdPlace)
            wrapper
        }
    }

    suspend fun delete(email: String, thirdPlace: ThirdPlaceModel) : ThirdPlaceWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(email, thirdPlace._id)
            wrapper
        }
    }
}