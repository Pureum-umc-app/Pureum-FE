package kr.co.pureum.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.HomeInfo
import kr.co.domain.model.UsageTimeDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemHomeUsageTimeBinding

class UsageTimeAdapter : RecyclerView.Adapter<UsageTimeAdapter.ViewHolder>() {
    private lateinit var binding: ItemHomeUsageTimeBinding
    private var homeInfoList = mutableListOf<HomeInfo>()

    inner class ViewHolder(val binding: ItemHomeUsageTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(homeInfo: HomeInfo){
            binding.homeInfo = homeInfo
        }
    }

    override fun getItemCount(): Int = homeInfoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_home_usage_time, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(homeInfoList[position])
    }

    fun setData(data: List<HomeInfo>) {
        homeInfoList.clear()
        homeInfoList.addAll(data)
        notifyItemRangeInserted(0, homeInfoList.size)
    }

    fun updateData(data: List<HomeInfo>) {
        homeInfoList.clear()
        homeInfoList.addAll(data)
        notifyItemChanged(homeInfoList.size - 1)
    }
}