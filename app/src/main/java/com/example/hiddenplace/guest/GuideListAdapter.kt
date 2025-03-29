package com.example.hiddenplace.guest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddenplace.R




class GuideListAdapter(private val onItemClick: (GuideListModel) -> Unit) :
    ListAdapter<GuideListModel, GuideListAdapter.GuideViewHolder>(GuideDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guide_list_item, parent, false)
        return GuideViewHolder(view, onItemClick) // 클릭 리스너 전달
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        val guide = getItem(position)
        holder.bind(guide)
    }

    class GuideViewHolder(itemView: View, private val onItemClick: (GuideListModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val profileTextView: TextView = itemView.findViewById(R.id.profileTextView)
        private val regionTextView: TextView = itemView.findViewById(R.id.regionTextView)
        private val guideItemLayout: LinearLayout = itemView.findViewById(R.id.guideItemLayout) // XML에서 id 확인 필수!

        fun bind(guide: GuideListModel) {
            userNameTextView.text = "${guide.userName} 가이드"
            profileTextView.text = guide.introduction
            regionTextView.text = NumToRegion.getRegionName(guide.regionId)

            // 🔥 클릭 이벤트 추가
            guideItemLayout.setOnClickListener {
                onItemClick(guide)
            }
        }
    }
}

class GuideDiffCallback : DiffUtil.ItemCallback<GuideListModel>() {
    override fun areItemsTheSame(oldItem: GuideListModel, newItem: GuideListModel): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: GuideListModel, newItem: GuideListModel): Boolean {
        return oldItem == newItem
    }
}