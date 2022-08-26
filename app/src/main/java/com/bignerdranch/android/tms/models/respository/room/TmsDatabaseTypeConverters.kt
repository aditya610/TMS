package com.bignerdranch.android.tms.models.respository.room

import androidx.room.TypeConverter
import java.util.*

class TmsDatabaseTypeConverters {

    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string: String?): UUID? {
        return UUID.fromString(string)
    }
}