package ie.por.thirdplace2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ie.por.thirdplace2.data.ThirdPlaceModel

@Database(entities = [ThirdPlaceModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getThirdPlaceDAO(): ThirdPlaceDAO
}