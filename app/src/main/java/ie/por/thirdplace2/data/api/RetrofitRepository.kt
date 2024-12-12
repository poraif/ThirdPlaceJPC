package ie.por.thirdplace2.data.api

import ie.por.thirdplace2.data.ThirdPlaceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject
constructor(private val serviceApi: ThirdPlaceService)  {

    suspend fun getAll(): List<ThirdPlaceModel>
    {
        return withContext(Dispatchers.IO) {
            val thirdPlaces = serviceApi.getall()
            thirdPlaces.body() ?: emptyList()
        }
    }

    suspend fun get(id: String): List<ThirdPlaceModel>
    {
        return withContext(Dispatchers.IO) {
            val thirdPlace = serviceApi.get(id)
            thirdPlace.body() ?: emptyList()
        }
    }

    suspend fun insert(thirdPlace: ThirdPlaceModel) : ThirdPlaceWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(thirdPlace)
            wrapper
        }
    }

    suspend fun update(thirdPlace: ThirdPlaceModel) : ThirdPlaceWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(thirdPlace._id,thirdPlace)
            wrapper
        }
    }

    suspend fun delete(thirdPlace: ThirdPlaceModel) : ThirdPlaceWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(thirdPlace._id)
            wrapper
        }
    }
}