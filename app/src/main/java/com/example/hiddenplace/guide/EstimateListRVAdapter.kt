package com.example.hiddenplace.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.R

class EstimateListRVAdapter(
    private val estimateItems: List<EstimateItem>,
    private val onItemClicked: (EstimateItem) -> Unit // 아이템 클릭 콜백
) : RecyclerView.Adapter<EstimateListRVAdapter.EstimateViewHolder>() {

    inner class EstimateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userNameTextView: TextView = view.findViewById(R.id.tvUserName)
        val regionTextView: TextView = view.findViewById(R.id.tvRegion)
        val editImageView: ImageView = view.findViewById(R.id.ivEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstimateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.estimate_list_item, parent, false)
        return EstimateViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstimateViewHolder, position: Int) {
        val estimateItem = estimateItems[position]
        holder.userNameTextView.text = estimateItem.userName
        holder.regionTextView.text = estimateItem.region

        // 아이템 클릭 이벤트
        holder.itemView.setOnClickListener {
            onItemClicked(estimateItem)
        }
    }

    override fun getItemCount(): Int = estimateItems.size
}