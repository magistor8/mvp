package com.magistor8.translator.view.fragment_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.magistor8.translator.R
import com.magistor8.translator.data.room.HistoryEntity
import com.magistor8.translator.databinding.FragmentHistoryBinding
import com.magistor8.translator.domain.HistoryFragmentContract
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class HistoryFragment : Fragment(){

    private val viewModel: HistoryFragmentViewModel by viewModel(named("HistoryFragment"))

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val adapter = HistoryAdapter(
        object : HistoryAdapter.OnListItemClickListener {
            override fun onItemClick(data: HistoryEntity) {

            }
        },
        listOf()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.historyRecyclerview.adapter = adapter

        //Вернем историю запросов
        viewModel.onEvent(HistoryFragmentContract.Events.AllHistory)
        //ЛайвДата - рендер
        viewModel.viewState.observe(viewLifecycleOwner) { state -> renderData(state) }
    }

    private fun renderData(state: HistoryFragmentContract.ViewState) {
        when(state) {
            is HistoryFragmentContract.ViewState.Complete -> adapter.setData(state.data)
            is HistoryFragmentContract.ViewState.Error -> ""
        }
    }



}