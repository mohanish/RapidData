package com.rapidata.box.network

import com.rapidata.box.apimodels.SearchData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {

    @Headers(
        "x-rapidapi-host: contextualwebsearch-websearch-v1.p.rapidapi.com",
        "x-rapidapi-key: f5b42cd663msh374486b5e7eec62p19f7edjsnec582b17f586"
    )
    @GET("ImageSearchAPI?autoCorrect=false&pageSize=10&safeSearch=false")
    fun getSearchImgAsync(
        @Query("pageNumber") pageNumber: Int,
        @Query("q") searchText: String
    ): Deferred<SearchData>
}