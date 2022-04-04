package com.magistor8.translator.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.magistor8.translator.R
import com.magistor8.translator.databinding.ActivityMainBinding
import com.magistor8.translator.domain.MainContract
import com.magistor8.translator.domain.entities.DataModel
import com.magistor8.translator.view.search_dialog_fragment.SearchDialogFragment

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter(
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text,
                    Toast.LENGTH_SHORT).show()
            }
        },
        listOf()
    )

    //ViewModel
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object : SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.onEvent(MainContract.Events.GetData(searchWord))
                }
            })
            searchDialogFragment.show(supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

        //ЛайвДата - рендер
        viewModel.viewState.observe(this) { state -> renderData(state) }

    }

    private fun renderData(viewState: MainContract.ViewState) {
        when (viewState) {
            is MainContract.ViewState.SearchComplete -> {
                val dataModel = viewState.data
                if (dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter.setData(dataModel)
                }
            }
            is MainContract.ViewState.Loading -> {
                showViewLoading()
                if (viewState.progress != 0) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = viewState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is MainContract.ViewState.Error -> {
                showErrorScreen(viewState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.onEvent(MainContract.Events.GetData("hi"))
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }
    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }
    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }
}