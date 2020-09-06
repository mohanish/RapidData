package com.rapidata.box.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rapidata.box.apimodels.SearchValue

@Dao
interface SearchDao {

    @Query("SELECT * FROM search_img_db")
    fun findAll(): LiveData<List<SearchValue>>

    @Transaction
    fun updateAll(searchResult: List<SearchValue>) {
        deleteAll()
        add(searchResult = searchResult)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(searchResult: List<SearchValue>)

    @Query("DELETE FROM search_img_db")
    abstract fun deleteAll()
}