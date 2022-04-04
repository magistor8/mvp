package com.magistor8.mvp.data.retrofit

import com.magistor8.mvp.domain.entities.DataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*


interface Api {
    @GET("words/search")
    fun search(
        @Query("search") word: String
    ): Observable<List<DataModel>>
}
