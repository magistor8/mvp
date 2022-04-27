package com.magistor8.translator.data.retrofit

import com.magistor8.translator.domain.entities.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface Api {
    @GET("words/search")
    fun search(
        @Query("search") word: String
    ): Deferred<List<DataModel>>
}
