package com.magistor8.mvp.domain

import com.magistor8.mvp.domain.entities.DataModel
import io.reactivex.rxjava3.core.Observable

interface Repo {
    fun getData(word: String) : Observable<List<DataModel>>
}