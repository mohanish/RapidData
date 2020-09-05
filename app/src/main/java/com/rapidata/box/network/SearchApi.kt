package com.rapidata.box.network

import com.rapidata.box.apimodels.SearchData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface SearchApi {

    @GET("users")
    fun getSearchImgAsync(): Deferred<List<SearchData>>
}