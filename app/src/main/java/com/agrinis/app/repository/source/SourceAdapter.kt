package com.agrinis.app.repository.source

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrinis.app.data.models.response.Sources
import com.agrinis.app.databinding.ItemSourcesBinding

class SourceAdapter(
    private val onClick: (Sources) -> Unit
) : ListAdapter<Sources, SourceAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSourcesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSourcesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Sources) {
            binding.apply {
                tvSourceName.text = data.name
                tvSourceDesc.text = data.description
                itemView.setOnClickListener {
                    onClick(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Sources> =
            object : DiffUtil.ItemCallback<Sources>() {
                override fun areItemsTheSame(oldItem: Sources, newItem: Sources): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Sources, newItem: Sources): Boolean {
                    return oldItem == newItem
                }
            }
    }
}