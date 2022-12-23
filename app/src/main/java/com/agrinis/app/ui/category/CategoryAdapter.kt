package com.agrinis.app.ui.category

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agrinis.app.data.models.Category
import com.agrinis.app.databinding.ItemCategoryBinding
import com.bumptech.glide.Glide

class CategoryAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<Category, CategoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("Range")
        fun bind(data: Category) {
            binding.apply {
                tvCategoryName.text = data.name
                cardCategoryLayout.setCardBackgroundColor(Color.parseColor(data.backgroundColor))
                Glide.with(itemView.context)
                    .load(data.icon)
                    .into(imgCategoryIcon)
                itemView.setOnClickListener {
                    onClick(data.name.toString())
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Category> =
            object : DiffUtil.ItemCallback<Category>() {
                override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                    return oldItem.name == newItem.name
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                    return oldItem == newItem
                }
            }
    }
}