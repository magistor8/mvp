package com.magistor8.translator.domain

import com.magistor8.translator.domain.entities.DataModel
import io.reactivex.rxjava3.core.Observable

interface Repo {
    suspend fun getData(word: String) : List<DataModel>
}