package kr.co.pureum.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.UserRankDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemHomeRankBinding

class RankingAdapter : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    private lateinit var binding: ItemHomeRankBinding
    private var rankList = mutableListOf<UserRankDto>()

    inner class ViewHolder(val binding: ItemHomeRankBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userRankInfo: UserRankDto, position: Int){
            with(binding) {
                userRankDto = userRankInfo
                rank = position + 1
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

    fun setData(data: List<UserRankDto>) {
        rankList.clear()
        rankList.addAll(data)
        notifyDataSetChanged()
    }
}