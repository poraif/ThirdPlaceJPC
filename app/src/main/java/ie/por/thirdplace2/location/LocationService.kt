package ie.por.thirdplace2.location

import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun getLocationFlow(): Flow<Location?>
}