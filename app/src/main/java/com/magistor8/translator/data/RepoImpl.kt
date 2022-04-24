package com.magistor8.translator.data

import com.magistor8.translator.data.retrofit.RemoteDataSource
import com.magistor8.translator.domain.Repo
import com.magistor8.translator.domain.entities.DataModel
import io.reactivex.rxjava3.core.Observable

class RepoImpl(private val dataSource: RemoteDataSource) : Repo {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.search(word)
    }
}