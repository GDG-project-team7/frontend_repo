package com.example.hiddenplace.guide


class PortpolioRVAdapter(
    private val portfolioList: List<Portfolio>,
    private val onItemChecked: (Portfolio) -> Unit
) : RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>() {

    private var selectedPosition: Int = -1 // 선택된 항목 위치

    class PortfolioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val regionText: TextView = itemView.findViewById(R.id.regionText)
        val selectCheckBox: CheckBox = itemView.findViewById(R.id.selectCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_portfolio, parent, false)
        return PortfolioViewHolder(view)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        val portfolio = portfolioList[position]
        holder.titleText.text = portfolio.title
        holder.regionText.text = portfolio.region

        // 체크박스 상태 설정
        holder.selectCheckBox.isChecked = (position == selectedPosition)

        // 체크박스 클릭 이벤트 처리
        holder.selectCheckBox.setOnClickListener {
            if (position == selectedPosition) {
                selectedPosition = -1 // 선택 해제
            } else {
                selectedPosition = position // 새로운 항목 선택
                onItemChecked(portfolio) // 선택된 항목 전달
            }
            notifyDataSetChanged() // 데이터 갱신
        }
    }

    override fun getItemCount(): Int = portfolioList.size
}
