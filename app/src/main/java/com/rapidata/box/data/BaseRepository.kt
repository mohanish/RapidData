package com.rapidata.box.data

import com.rapidata.box.database.SearchDao
import com.rapidata.box.network.SearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseRepository(private val searchApi: SearchApi, private val searchDao: SearchDao) {

    val searchResultData = searchDao.findAll()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val searchResult = searchApi.getSearchImgAsync().await()
            searchDao.updateAll(searchResult.value)
        }
    }
}