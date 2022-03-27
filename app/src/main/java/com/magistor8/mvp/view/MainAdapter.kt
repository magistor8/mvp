package com.magistor8.mvp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magistor8.mvp.databinding.ActivityMainRecyclerviewItemBinding
import com.magistor8.mvp.domain.entities.DataModel

class MainAdapter(
    private val onItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(newData: List<DataModel>) {
        this.data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(ActivityMainRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: ActivityMainRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextviewRecyclerItem.text = data.text
                binding.descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.translation
                binding.root.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onItemClickListener.onItemClick(listItemData)
    }
    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}