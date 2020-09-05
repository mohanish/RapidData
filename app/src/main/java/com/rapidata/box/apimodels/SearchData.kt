package com.rapidata.box.apimodels

import androidx.annotation.Keep
import androidx.room.Entity

@Entity(tableName = "search_img_db")
@Keep
data class SearchData(
    val _type: String,
    val value: List<Value>
)

@Keep
data class Value(
    val base64Encoding: Any,
    val height: Int,
    val thumbnail: String,
    val thumbnailHeight: Int,
    val thumbnailWidth: Int,
    val url: String,
    val width: Int
)