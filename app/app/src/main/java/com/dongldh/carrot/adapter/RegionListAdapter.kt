package com.dongldh.carrot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dongldh.carrot.data.Region
import com.dongldh.carrot.databinding.ItemRegionListBinding

class RegionListAdapter : PagedListAdapter<Region, RecyclerView.ViewHolder>(RegionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RegionListViewHolder(ItemRegionListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val region = getItem(position)

        if(region != null) {
            (holder as RegionListViewHolder).bind(region)
        }
    }

    inner class RegionListViewHolder(val binding: ItemRegionListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Region) {
            binding.apply {
                region = item
                executePendingBindings()
            }
        }
    }
}

private class RegionDiffCallback: DiffUtil.ItemCallback<Region>() {
    override fun areItemsTheSame(oldItem: Region, newItem: Region): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Region, newItem: Region): Boolean {
        return oldItem == newItem
    }

}

