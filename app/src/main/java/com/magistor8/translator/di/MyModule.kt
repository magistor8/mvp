package com.magistor8.translator.di

import com.magistor8.translator.data.RepoImpl
import com.magistor8.translator.data.retrofit.RemoteDataSource
import com.magistor8.translator.domain.Repo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyModule {

    @Singleton
    @Provides
    fun repo() : Repo {
        return RepoImpl(RemoteDataSource())
    }

}