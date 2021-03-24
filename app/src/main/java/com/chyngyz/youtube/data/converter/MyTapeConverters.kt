package com.chyngyz.youtube.data.converter

import androidx.room.TypeConverter
import com.chyngyz.youtube.data.model.Info
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MyTapeConverters {

        @TypeConverter
        fun fromString(value: String?): MutableList<Info>? {
            val listType: Type = object : TypeToken<MutableList<Info>?>() {}.getType()
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayList(list: MutableList<Info>?): String? {
            val gson = Gson()
            val listType: Type = object : TypeToken<MutableList<Info>?>() {}.getType()
            return gson.toJson(list, listType)
        }

}