package com.magistor8.translator.view.fragment_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magistor8.core.room.HistoryEntity
import com.magistor8.translator.databinding.HistoryFragmentRecyclerviewItemBinding

class HistoryAdapter(
    private val onItemClickListener: OnListItemClickListener,
    private var data: List<HistoryEntity>
) : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    fun setData(newData: List<HistoryEntity>) {
        this.data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(HistoryFragmentRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: HistoryFragmentRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HistoryEntity) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextviewRecyclerItem.text = data.search
                binding.root.setOnClickListener { details(data) }
            }
        }
    }

    private fun details(data: HistoryEntity) {
        onItemClickListener.onItemClick(data)
    }
    interface OnListItemClickListener {
        fun onItemClick(data: HistoryEntity)
    }

}