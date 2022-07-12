package com.magistor8.translator.domain

import androidx.lifecycle.LiveData

interface MainFragmentContract {

    sealed interface ViewState {

    }

    sealed interface Events {
        data class GetData(val word: String): Events
    }

    interface ViewModelInterface {
        val viewState: LiveData<ViewState>
        fun onEvent(event: Events)
    }
}