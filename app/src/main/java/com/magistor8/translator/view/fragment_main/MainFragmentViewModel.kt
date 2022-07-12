package com.magistor8.translator.view.fragment_main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magistor8.translator.domain.BaseViewModel
import com.magistor8.translator.domain.MainFragmentContract

class MainFragmentViewModel : BaseViewModel(), MainFragmentContract.ViewModelInterface {

    override val viewState: LiveData<MainFragmentContract.ViewState> = MutableLiveData()

    override fun onEvent(event: MainFragmentContract.Events) {

    }

}