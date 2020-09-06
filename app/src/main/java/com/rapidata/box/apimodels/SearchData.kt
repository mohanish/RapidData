package com.rapidata.box.apimodels

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Keep
data class SearchData(
    @SerializedName("_type")
    val _type: String,
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("value")
    var value: MutableList<SearchValue> = mutableListOf()
)

@Keep
@Entity(tableName = "search_img_db")
data class SearchValue(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String?,
    val name: String?,
    val base64Encoding: String?,
    val imageWebSearchUrl: String?,
    val height: Int,
    val thumbnail: String?,
    val thumbnailHeight: Int,
    val thumbnailWidth: Int,
    val url: String?,
    val width: Int
)

class SearchValueConverter {
    private val gson = Gson()

    @TypeConverter
    fun listToJson(searchValue: MutableList<SearchValue>): String? = gson.toJson(searchValue)

    @TypeConverter
    fun jsonToList(searchValue: String): MutableList<SearchValue> {
        val type = object : TypeToken<MutableList<SearchValue>>() {}.type
        return gson.fromJson(searchValue, type)
    }
}