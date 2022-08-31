package com.example.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemHeroBinding
import com.example.presentation.ui.model.HeroVo

class HeroListRecyclerViewAdapter(
    private val values: MutableSet<HeroVo> = mutableSetOf(),
    private val onItemClicked: (Int) -> Unit = {}
) : RecyclerView.Adapter<HeroListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values.elementAt(position)
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.name
        holder.itemView.setOnClickListener { onItemClicked(item.id) }
    }

    override fun getItemCount(): Int = values.size

    fun add(items: List<HeroVo>) {
        values.addAll(items)
        notifyItemRangeInserted(values.size, items.size)
    }

    inner class ViewHolder(binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
    }

}