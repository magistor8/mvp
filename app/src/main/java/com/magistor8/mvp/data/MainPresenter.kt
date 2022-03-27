package com.magistor8.mvp.data

import com.magistor8.mvp.domain.MainContract

class MainPresenter : MainContract.Presenter {

    override fun attachView(view: MainContract.View) {
        TODO("Not yet implemented")
    }

    override fun detachView(view: MainContract.View) {
        TODO("Not yet implemented")
    }

    override fun onEvent(event: MainContract.Event) {
        when(event) {
            is MainContract.Event.getData -> ""
        }
    }
}