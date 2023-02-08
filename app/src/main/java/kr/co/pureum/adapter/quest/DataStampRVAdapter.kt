package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.DataStamp
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemStempBinding

class DataStampRVAdapter(private var dataStampList: ArrayList<DataStamp>) : RecyclerView.Adapter<DataStampRVAdapter.DataStampViewHolder>() {
    inner class DataStampViewHolder(private val binding: ItemStempBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataStamp: DataStamp) {
            binding.itemStamp.setImageResource(R.drawable.stamp_gray)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataStampViewHolder {
        val binding = ItemStempBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataStampViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataStampViewHolder, position: Int) {
        holder.bind(dataStampList[position])
    }

    override fun getItemCount(): Int = dataStampList.size

    fun setData(data: ArrayList<DataStamp>) {
        dataStampList.clear()
        dataStampList.addAll(data)
        notifyDataSetChanged()
    }
}