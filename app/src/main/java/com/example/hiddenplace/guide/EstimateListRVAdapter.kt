package com.example.hiddenplace.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.databinding.EstimateListItemBinding

class EstimateListRVAdapter(private val estimateList: List<Estimate>) :
    RecyclerView.Adapter<EstimateListRVAdapter.EstimateViewHolder>() {

    class EstimateViewHolder(private val binding: EstimateListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(estimate: Estimate) {
            binding.UserId.text = "${estimate.user.userId}"
            binding.Region.text = "${estimate.user.region}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstimateViewHolder {
        val binding = EstimateListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EstimateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EstimateViewHolder, position: Int) {
        holder.bind(estimateList[position])
    }

    override fun getItemCount() = estimateList.size
}