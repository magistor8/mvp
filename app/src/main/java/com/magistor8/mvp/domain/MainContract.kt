package com.magistor8.mvp.domain

import com.magistor8.mvp.domain.entities.DataModel

interface MainContract {

    sealed interface ViewState {
        data class Loading(val progress: Int): ViewState
        data class SearchComplete(val data: List<DataModel>) : ViewState
        data class Error(val error: Throwable): ViewState
    }

    sealed interface Events {
        data class GetData(val word: String): Events
    }

    interface View {
        fun renderData(viewState: ViewState)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView(view: View)
        fun onEvent(event: Events)
    }
}