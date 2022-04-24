package com.magistor8.translator.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magistor8.translator.domain.MainContract
import com.magistor8.translator.domain.Repo
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : MainContract.ViewModelInterface, ViewModel(), KoinComponent {

    private val repository : Repo by inject()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewState.mutable().postValue(MainContract.ViewState.Error(throwable))
    }

    override val viewState: LiveData<MainContract.ViewState> = MutableLiveData()

    override fun onEvent(event: MainContract.Events) {
        when(event) {
            is MainContract.Events.GetData -> loadData(event.word)
        }
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData<T>
    }

    private fun loadData(word: String) {
        viewState.mutable().postValue(MainContract.ViewState.Loading(0))
        viewModelScope.async (coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                val result = repository.getData(word)
                viewState.mutable().postValue(MainContract.ViewState.SearchComplete(result))
                repository.saveDataToDb(word, result)
            }
        }
    }

}