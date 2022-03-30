package com.magistor8.mvp.data

import com.magistor8.mvp.data.retrofit.RemoteDataSource
import com.magistor8.mvp.domain.Repo
import com.magistor8.mvp.domain.entities.DataModel
import io.reactivex.rxjava3.core.Observable

class RepoImpl(private val dataSource: RemoteDataSource) : Repo {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.search(word)
    }
}