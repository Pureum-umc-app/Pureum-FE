package kr.co.pureum.adapter.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.UsageTimeInfo
import kr.co.pureum.R
import kr.co.pureum.databinding.FragmentUsageTimeBinding
import java.time.format.DateTimeFormatter

class UsageTimeAdapter : RecyclerView.Adapter<UsageTimeAdapter.ViewHolder>() {
    private lateinit var binding: FragmentUsageTimeBinding
    private var usageTimeList = mutableListOf<UsageTimeInfo>()

    interface Listener {
        fun onClick(combId: Long)
        fun onDelete(combId: Long)
        fun onPost(combId: Long)
    }

    private lateinit var usageTimeListener : Listener

    fun setListener(listener: Listener){
        usageTimeListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_usage_time, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usageTimeList[position], position)
//        holder.itemView.setOnClickListener { usageTimeListener.onClick(usageTimeList[position].id) }
    }

    override fun getItemCount(): Int = usageTimeList.size

    fun setData(data: List<UsageTimeInfo>){
        usageTimeList.clear()
        usageTimeList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: FragmentUsageTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(usageTimeInfo: UsageTimeInfo, position: Int){
            binding.homeDate.text = usageTimeInfo.dateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
            binding.homeUsageTimeIndicator.max = usageTimeInfo.goalTime
            binding.homeUsageTimeIndicator.progress = usageTimeInfo.usageTime
        }
    }
}