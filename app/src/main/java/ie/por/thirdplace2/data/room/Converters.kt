package ie.por.thirdplace2.data.room

import android.net.Uri
import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromUri(value: String?): Uri? {
        return if (value == null) null else Uri.parse(value)
    }

    @TypeConverter
    fun toUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun fromAmenityList(amenities: List<String>?): String? {
        return amenities?.joinToString(",")
    }

    @TypeConverter
    fun toAmenityList(amenitiesString: String?): List<String>? {
        return amenitiesString?.split(",")
    }

}