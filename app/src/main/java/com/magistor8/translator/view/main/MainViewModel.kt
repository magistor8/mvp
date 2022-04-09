package com.magistor8.translator.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magistor8.translator.domain.MainContract
import com.magistor8.translator.domain.Repo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : MainContract.MyViewModelInterface, ViewModel(), KoinComponent {

    private val repository : Repo by inject()

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })


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
        viewModelCoroutineScope.async {
            val result = repository.getData(word)
            viewState.mutable().postValue(MainContract.ViewState.SearchComplete(result))
        }
    }

    private fun handleError(error: Throwable) {
        viewState.mutable().postValue(MainContract.ViewState.Error(error))
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }


}