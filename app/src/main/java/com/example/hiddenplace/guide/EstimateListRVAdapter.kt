package com.example.hiddenplace.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.databinding.EstimateListItemBinding

class EstimateListRVAdapter(
    private val estimateItems: List<Estimate>,
    private val onItemClicked: (Estimate) -> Unit // 아이템 클릭 콜백
) : RecyclerView.Adapter<EstimateListRVAdapter.EstimateViewHolder>() {

    inner class EstimateViewHolder(private val binding: EstimateListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(estimate: Estimate) {
            binding.UserName.text = estimate.user.userName
            binding.RegionId.text = estimate.user.regionId.toString()

            // 아이템 클릭 이벤트
            binding.root.setOnClickListener {
                onItemClicked(estimate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstimateViewHolder {
        val binding = EstimateListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EstimateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EstimateViewHolder, position: Int) {
        holder.bind(estimateItems[position])
    }

    override fun getItemCount(): Int = estimateItems.size
}
