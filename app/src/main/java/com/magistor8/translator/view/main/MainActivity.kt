package com.magistor8.translator.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.magistor8.translator.R
import com.magistor8.translator.data.room.HistoryDao
import com.magistor8.translator.databinding.ActivityMainBinding
import com.magistor8.translator.domain.MainContract
import com.magistor8.translator.utils.Navigation
import com.magistor8.translator.view.fragment_history.HistoryFragment
import com.magistor8.translator.view.fragment_main.MainFragment
import com.magistor8.translator.view.search_dialog_fragment.SearchDialogFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"
private const val BUNDLE = "BUNDLE"
private const val SS = "Hi"

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private var lastRequest = SS

    private val navigation : Navigation by inject { parametersOf(this)}
    private val historyDao : HistoryDao by inject()
    private val viewModel: MainViewModel by viewModel(named("Main"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object : SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    lastRequest = searchWord
                    viewModel.onEvent(MainContract.Events.GetData(searchWord))
                }
            })
            searchDialogFragment.show(supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

        setBottomViewListener()
        bottomNavigationViewSetup()

        //ЛайвДата - рендер
        viewModel.viewState.observe(this) { state -> renderData(state) }

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container);
        if (fragment != null) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun bottomNavigationViewSetup() {
        with(supportFragmentManager) {
            addOnBackStackChangedListener {
                when (findFragmentById(R.id.container)) {
                    is MainFragment -> binding.bottomNavigationView.menu.findItem(R.id.bottom_main).isChecked =
                        true
                    is HistoryFragment -> binding.bottomNavigationView.menu.findItem(R.id.bottom_history).isChecked =
                        true
                }
            }
        }
    }

    private fun setBottomViewListener() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_main -> {
                    navigation.navigate(
                        MainFragment::class.java,
                        Navigation.Action.REPLACE,
                        addToBS = true
                    )
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_history -> {
                    navigation.navigate(
                        HistoryFragment::class.java,
                        Navigation.Action.REPLACE,
                        addToBS = true
                    )
                    return@setOnItemSelectedListener true
                }
                else -> {return@setOnItemSelectedListener true}
            }
        }
    }

    private fun renderData(viewState: MainContract.ViewState) {
        when (viewState) {
            is MainContract.ViewState.SearchComplete -> {
                val dataModel = viewState.data
                if (dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showFragmentContainer()
                    navigation.navigate(
                        MainFragment::class.java,
                        Navigation.Action.REPLACE,
                        Bundle().apply {
                            putParcelableArrayList(BUNDLE, dataModel.toCollection(ArrayList()))
                        },
                        addToBS = true
                    )
                }
            }
            is MainContract.ViewState.Loading -> {
                showViewLoading()
                binding.progressBarHorizontal.visibility = GONE
                binding.progressBarRound.visibility = VISIBLE
            }
            is MainContract.ViewState.Error -> {
                binding.container.visibility = GONE
                showErrorScreen(viewState.error.message)
            }
        }
    }

    private fun showFragmentContainer() {
        binding.container.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.onEvent(MainContract.Events.GetData(lastRequest))
        }
    }

    private fun showViewError() {
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = VISIBLE
    }

}