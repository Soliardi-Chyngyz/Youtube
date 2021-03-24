package com.chyngyz.youtube.data.converter

import androidx.room.TypeConverter
import com.chyngyz.youtube.data.model.DetailsItem
import com.chyngyz.youtube.data.model.Info
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PlaylistConverter {
    @TypeConverter
    fun fromString(value: String?): MutableList<DetailsItem>? {
        val listType: Type = object : TypeToken<MutableList<DetailsItem>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: MutableList<DetailsItem>?): String? {
        val gson = Gson()
        val listType: Type = object : TypeToken<MutableList<DetailsItem>?>() {}.getType()
        return gson.toJson(list, listType)
    }
}