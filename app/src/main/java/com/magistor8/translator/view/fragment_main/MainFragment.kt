package com.magistor8.translator.view.fragment_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magistor8.translator.databinding.FragmentMainBinding
import com.magistor8.translator.domain.MainFragmentContract
import com.magistor8.core.domain.entities.DataModel
import com.magistor8.translator.R
import com.magistor8.translator.utils.Navigation
import com.magistor8.translator.utils.viewById
import com.magistor8.translator.view.fragment_details.DetailsFragment
import org.koin.android.ext.android.inject
import org.koin.android.scope.getOrCreateScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinScopeComponent
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

private const val BUNDLE = "BUNDLE"
private const val BUNDLE_DETAILS = "BUNDLE_DETAILS"

class MainFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()
    private val viewModel: MainFragmentViewModel by viewModel()
    private val navigation : Navigation by inject { parametersOf(this) }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //Использование делегата
    private val mainActivityRecyclerview by viewById<RecyclerView>(R.id.main_activity_recyclerview)

    private val adapter = MainAdapter(
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                navigation.navigate(
                    DetailsFragment::class.java,
                    Navigation.Action.REPLACE,
                    Bundle().apply {
                        putParcelable(BUNDLE_DETAILS, data)
                    },
                    addToBS = true
                )
            }
        },
        listOf()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityRecyclerview.layoutManager = LinearLayoutManager(context)
        mainActivityRecyclerview.adapter = adapter
        arguments?.let {
            loadData(it)
        }

        //ЛайвДата - рендер
        viewModel.viewState.observe(viewLifecycleOwner) { state -> renderData(state) }
    }

    private fun renderData(state: MainFragmentContract.ViewState) {

    }

    fun newData(data : List<DataModel>) {
        adapter.setData(data)
    }

    private fun loadData(bundle: Bundle) {
        val data = bundle.getParcelableArrayList<DataModel>(BUNDLE)?.toList()
        data?.let {
            adapter.setData(data)
            bundle.clear()
        }
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

}