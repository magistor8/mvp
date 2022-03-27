package com.magistor8.mvp.domain

import com.magistor8.mvp.domain.entities.DataModel

interface MainContract {

    sealed interface ViewState {
        object EmptyState: ViewState
        data class SearchComplete(val data: List<DataModel>) : ViewState
        data class Error(val error: Throwable): ViewState
    }

    sealed interface Event {
        class getData(word: String, isOnline: Boolean): Event
    }

    interface View {
        fun renderData(viewState: ViewState)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView(view: View)
        fun onEvent(event: Event)
    }
}