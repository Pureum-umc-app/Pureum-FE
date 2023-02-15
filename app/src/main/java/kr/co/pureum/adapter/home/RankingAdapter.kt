package kr.co.pureum.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.Rank
import kr.co.domain.model.UserRankDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemHomeRankBinding

class RankingAdapter : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    private lateinit var binding: ItemHomeRankBinding
    private var rankList = mutableListOf<Rank>()

    inner class ViewHolder(val binding: ItemHomeRankBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rank: Rank, position: Int){
            binding.rank = rank
            val scale = binding.rankLayout.resources.displayMetrics.density
            if (position == rankList.size - 1) {
                (binding.rankLayout.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = (12 * scale).toInt()
            } else {
                (binding.rankLayout.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = (4 * scale).toInt()
            }
        }
    }

    override fun getItemCount(): Int = rankList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_home_rank, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rankList[position], position)
    }

    fun setData(data: List<Rank>) {
        val prevSize = rankList.size
        rankList.clear()
        rankList.addAll(data)
        notifyDataSetChanged()
    }
}