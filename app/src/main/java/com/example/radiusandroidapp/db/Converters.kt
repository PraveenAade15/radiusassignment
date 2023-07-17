package com.example.radiusandroidapp.db
import androidx.room.TypeConverter
import com.example.radiusandroidapp.model.Exclusions
import com.example.radiusandroidapp.model.Facilities
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromFacilitiesList(facilities: List<Facilities>): String {
        return gson.toJson(facilities)
    }

    @TypeConverter
    fun toFacilitiesList(facilitiesString: String): List<Facilities> {
        val listType = object : TypeToken<List<Facilities>>() {}.type
        return gson.fromJson(facilitiesString, listType)
    }

    @TypeConverter
    fun fromExclusionsList(exclusions: List<List<Exclusions>>): String {
        return gson.toJson(exclusions)
    }

    @TypeConverter
    fun toExclusionsList(exclusionsString: String): List<List<Exclusions>> {
        val listType = object : TypeToken<List<List<Exclusions>>>() {}.type
        return gson.fromJson(exclusionsString, listType)
    }

    // Add other type converters for Options and Exclusions classes if needed
}
