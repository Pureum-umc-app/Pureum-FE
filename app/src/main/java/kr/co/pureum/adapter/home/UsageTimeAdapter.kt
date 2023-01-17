package kr.co.pureum.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.UsageTimeDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemHomeUsageTimeBinding

class UsageTimeAdapter(val context: Context) : RecyclerView.Adapter<UsageTimeAdapter.ViewHolder>() {
    private lateinit var binding: ItemHomeUsageTimeBinding
    private var usageList = mutableListOf<UsageTimeDto>()

    companion object {
        const val START_POSITION = 0
    }

    interface Listener {
        fun onLoad()
    }

    private lateinit var usageTimeListener : Listener

    fun setListener(listener: Listener){
        usageTimeListener = listener
    }

    inner class ViewHolder(val binding: ItemHomeUsageTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(usageTimeDto: UsageTimeDto){
            binding.usageTimeDto = usageTimeDto
        }
    }

    override fun getItemCount(): Int = usageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_home_usage_time, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usageList[position])
    }

    fun setData(data: List<UsageTimeDto>) {
        usageList.clear()
        usageList.addAll(data)
        notifyDataSetChanged()
    }
}