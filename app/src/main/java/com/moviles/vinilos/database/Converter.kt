package com.moviles.vinilos.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moviles.vinilos.models.Track

class Converters {

    @TypeConverter
    fun fromTrackList(value: MutableList<Track>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toTrackList(value: String): MutableList<Track> {
        val listType = object : TypeToken<MutableList<Track>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
