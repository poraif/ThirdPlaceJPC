package ie.por.thirdplace2.data.room

import ie.por.thirdplace2.data.ThirdPlaceModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject
constructor(private val thirdPlaceDAO: ThirdPlaceDAO) {
    fun getAll(): Flow<List<ThirdPlaceModel>>
            = thirdPlaceDAO.getAll()

    fun get(id: Int) = thirdPlaceDAO.get(id)

    suspend fun insert(thirdPlace: ThirdPlaceModel)
    { thirdPlaceDAO.insert(thirdPlace) }

    suspend fun update(thirdPlace: ThirdPlaceModel)
    { thirdPlaceDAO.update( thirdPlace.id,
                            thirdPlace.title,
                            thirdPlace.description,
                            thirdPlace.amenities,
                            thirdPlace.type,
                            thirdPlace.image,
                            thirdPlace.lat,
                            thirdPlace.lng,
                            thirdPlace.zoom) }

    suspend fun delete(thirdPlace: ThirdPlaceModel)
    { thirdPlaceDAO.delete(thirdPlace) }
}