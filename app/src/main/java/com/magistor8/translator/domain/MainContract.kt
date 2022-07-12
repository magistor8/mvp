package com.magistor8.translator.domain

import androidx.lifecycle.LiveData
import com.magistor8.core.domain.entities.DataModel

interface MainContract {

    sealed interface ViewState {
        data class Loading(val progress: Int): ViewState
        data class SearchComplete(val data: List<DataModel>) : ViewState
        data class Error(val error: Throwable): ViewState
    }

    sealed interface Events {
        data class GetData(val word: String): Events
    }

    interface ViewModelInterface {
        val viewState: LiveData<ViewState>
        fun onEvent(event: Events)
    }
}