package com.magistor8.translator.di

import androidx.room.Room
import com.google.gson.Gson
import com.magistor8.translator.data.RepoImpl
import com.magistor8.translator.data.retrofit.RemoteDataSource
import com.magistor8.translator.data.room.HistoryDataBase
import com.magistor8.translator.domain.Repo
import com.magistor8.translator.utils.Navigation
import com.magistor8.translator.view.fragment_details.DetailsFragmentViewModel
import com.magistor8.translator.view.fragment_history.HistoryFragmentViewModel
import com.magistor8.translator.view.fragment_main.MainFragmentViewModel
import com.magistor8.translator.view.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DB_NAME = "History.db"

val myModule = module {
    single { Gson() }
    single { params ->Navigation(params.get()) }
    single { RemoteDataSource() }
    single<Repo> { RepoImpl(get(), get()) }
    single{ Room.databaseBuilder(
        androidContext(),
        HistoryDataBase::class.java,
        DB_NAME
        ).build().historyDao()
    }
    viewModel(named("Main")) { MainViewModel() }
    viewModel(named("MainFragment")) { MainFragmentViewModel() }
    viewModel(named("HistoryFragment")) { HistoryFragmentViewModel() }
    viewModel(named("DetailsFragment")) { DetailsFragmentViewModel() }

}