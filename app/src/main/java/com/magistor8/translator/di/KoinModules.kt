package com.magistor8.translator.di

import androidx.room.Room
import com.google.gson.Gson
import com.magistor8.core.data.RepoImpl
import com.magistor8.core.data.retrofit.RemoteDataSource
import com.magistor8.core.domain.Repo
import com.magistor8.core.room.Converter
import com.magistor8.core.room.HistoryDataBase
import com.magistor8.translator.utils.Navigation
import com.magistor8.translator.view.fragment_details.DetailsFragment
import com.magistor8.translator.view.fragment_details.DetailsFragmentViewModel
import com.magistor8.translator.view.fragment_history.HistoryFragment
import com.magistor8.translator.view.fragment_history.HistoryFragmentViewModel
import com.magistor8.translator.view.fragment_main.MainFragment
import com.magistor8.translator.view.fragment_main.MainFragmentViewModel
import com.magistor8.translator.view.main.MainActivity
import com.magistor8.translator.view.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DB_NAME = "History.db"

val myModule = module {

    scope<MainActivity> {
        viewModel { MainViewModel() }
    }

    scope<MainFragment> {
        viewModel { MainFragmentViewModel() }
    }

    scope<HistoryFragment> {
        viewModel { HistoryFragmentViewModel() }
    }

    scope<DetailsFragment> {
        viewModel { DetailsFragmentViewModel() }
    }

    single<Repo> { RepoImpl(dataSource = get(), historyDao = get()) }
    single { Gson() }
    single { params -> Navigation(context = params.get()) }
    single { RemoteDataSource() }

    single{ Room.databaseBuilder(
        androidContext(),
        HistoryDataBase::class.java,
        DB_NAME
        ).addTypeConverter(Converter(gson = get()))
        .build().historyDao()
    }

}