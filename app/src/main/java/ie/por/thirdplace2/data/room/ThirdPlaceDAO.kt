package ie.por.thirdplace2.data.room

import android.net.Uri
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ie.por.thirdplace2.data.ThirdPlaceModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ThirdPlaceDAO {

    @Query("SELECT * FROM thirdplacemodel")
    fun getAll(): Flow<List<ThirdPlaceModel>>

    @Query("SELECT * FROM thirdplacemodel WHERE id=:id")
    fun get(id: Int): Flow<ThirdPlaceModel>

    @Insert
    suspend fun insert(thirdPlace: ThirdPlaceModel)

    @Query("""
    UPDATE thirdplacemodel
    SET title = :title,
        description = :description,
        amenities = :amenities,
        type = :type,
        image = :image,
        lat = :lat,
        lng = :lng,
        zoom = :zoom
    WHERE id = :id
""")
    suspend fun update(
        id: String,
        title: String,
        description: String,
        amenities: List<String>,
        type: String,
        image: Uri,
        lat: Double,
        lng: Double,
        zoom: Float
    )

    @Delete
    suspend fun delete(thirdPlace: ThirdPlaceModel)
}