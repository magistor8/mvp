package com.magistor8.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.magistor8.mvp.data.MainPresenter
import com.magistor8.mvp.databinding.ActivityMainBinding
import com.magistor8.mvp.domain.MainContract

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter by lazy {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun renderData(viewState: MainContract.ViewState) {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}