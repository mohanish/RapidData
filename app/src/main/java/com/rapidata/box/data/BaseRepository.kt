package com.rapidata.box.data

import com.rapidata.box.database.SearchDao
import com.rapidata.box.network.SearchApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseRepository(private val searchApi: SearchApi, private val searchDao: SearchDao) {

    val searchResultData = searchDao.findAll()

    suspend fun refresh(pageNumber: Int, searchText: String) {
        withContext(Dispatchers.IO) {
            val searchResult = searchApi.getSearchImgAsync(pageNumber, searchText).await()
            if (pageNumber == 1) searchDao.updateAll(searchResult = searchResult.value)
            else searchDao.add(searchResult.value)
        }
    }
}