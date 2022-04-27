package com.magistor8.translator.view.fragment_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magistor8.translator.domain.HistoryFragmentContract
import com.magistor8.translator.domain.Repo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailsFragmentViewModel : ViewModel(), HistoryFragmentContract.ViewModelInterface, KoinComponent {

    private val repository : Repo by inject()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewState.mutable().postValue(HistoryFragmentContract.ViewState.Error(throwable))
    }

    override val viewState: LiveData<HistoryFragmentContract.ViewState> = MutableLiveData()

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData<T>
    }

    override fun onEvent(event: HistoryFragmentContract.Events) {
        when(event) {
            HistoryFragmentContract.Events.AllHistory -> getAllHistory()
        }
    }

    private fun getAllHistory() {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                val historyEntities = repository.getAllData()
                viewState.mutable().postValue(HistoryFragmentContract.ViewState.Complete(historyEntities))
            }
        }
    }

}