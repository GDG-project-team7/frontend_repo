package com.example.hiddenplace.guest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.R

class GuideListAdapter :  ListAdapter<GuideListModel, GuideListAdapter.GuideViewHolder>(GuideDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guide_list_item, parent, false)
        return GuideViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        val guide = getItem(position)
        holder.bind(guide)
    }

    class GuideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val profileTextView: TextView = itemView.findViewById(R.id.profileTextView)
        private val regionTextView: TextView = itemView.findViewById(R.id.regionTextView)

        fun bind(guide: GuideListModel) {
            userNameTextView.text = guide.userName
            profileTextView.text = guide.profileText
            regionTextView.text = NumToRegion.getRegionName(guide.regionId) // 변환된 한글 지역명 표시
        }
    }
}

class GuideDiffCallback : DiffUtil.ItemCallback<GuideListModel>() {
    override fun areItemsTheSame(oldItem: GuideListModel, newItem: GuideListModel): Boolean {
        return oldItem.userName == newItem.userName // 고유값으로 비교
    }

    override fun areContentsTheSame(oldItem: GuideListModel, newItem: GuideListModel): Boolean {
        return oldItem == newItem
    }
}