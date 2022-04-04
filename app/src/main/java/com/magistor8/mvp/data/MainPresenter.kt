package com.magistor8.mvp.data

import com.magistor8.mvp.App
import com.magistor8.mvp.domain.MainContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {

    private var currentView: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: MainContract.View) {
        if (view == currentView) {
            currentView = null
        }
    }

    override fun onEvent(event: MainContract.Events) {
        when(event) {
            is MainContract.Events.GetData -> loadData(event.word)
        }
    }

    private fun loadData(word: String) {
        currentView?.renderData(MainContract.ViewState.Loading(0))
        App.instance.repo.getData(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
            onError = {
                currentView?.renderData(MainContract.ViewState.Error(it))
            },
            onNext = {
                currentView?.renderData(MainContract.ViewState.SearchComplete(it))
            }
        )
    }
}