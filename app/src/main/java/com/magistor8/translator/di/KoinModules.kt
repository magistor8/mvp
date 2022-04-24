package com.magistor8.translator.di

import com.magistor8.translator.data.RepoImpl
import com.magistor8.translator.data.retrofit.RemoteDataSource
import com.magistor8.translator.domain.Repo
import com.magistor8.translator.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    single { RemoteDataSource() }
    single<Repo> { RepoImpl(get()) }
    viewModel { MainViewModel() }
}