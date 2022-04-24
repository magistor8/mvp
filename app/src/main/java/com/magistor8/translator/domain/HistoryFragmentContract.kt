package com.magistor8.translator.domain

import androidx.lifecycle.LiveData
import com.magistor8.translator.data.room.HistoryEntity
import com.magistor8.translator.domain.entities.DataModel

interface HistoryFragmentContract {

    sealed interface ViewState {
        data class Error(val throwable: Throwable) : ViewState
        data class Complete(val data: List<HistoryEntity>) : ViewState
    }

    sealed interface Events {
        object AllHistory: Events
    }

    interface ViewModelInterface {
        val viewState: LiveData<ViewState>
        fun onEvent(event: Events)
    }
}