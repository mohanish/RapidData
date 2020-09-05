package com.rapidata.box.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rapidata.box.apimodels.SearchData

@Dao
interface SearchDao {

    companion object {
        const val SEARCH_IMG_TABLE = "search_img"
    }

    @Query("SELECT * FROM $SEARCH_IMG_TABLE")
    fun findAll(): LiveData<List<SearchData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<SearchData>)
}