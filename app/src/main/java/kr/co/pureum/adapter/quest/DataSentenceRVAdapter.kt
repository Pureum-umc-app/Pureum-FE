package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.pureum.databinding.ItemSentenceBinding

class DataSentenceRVAdapter : RecyclerView.Adapter<DataSentenceRVAdapter.DataSentenceViewHolder>() {
    private var dataSentenceList = mutableListOf<String>()

    inner class DataSentenceViewHolder(private val binding: ItemSentenceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: String, position: Int) {
            binding.keyword = keyword
            if (position == 0) {
                val scale = binding.sentenceLayout.resources.displayMetrics.density
                (binding.sentenceLayout.layoutParams as ViewGroup.MarginLayoutParams).marginStart = (24 * scale).toInt()
            } else if (position == dataSentenceList.size - 1) {
                val scale = binding.sentenceLayout.resources.displayMetrics.density
                (binding.sentenceLayout.layoutParams as ViewGroup.MarginLayoutParams).marginEnd = (24 * scale).toInt()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataSentenceViewHolder {
        val binding = ItemSentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataSentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataSentenceViewHolder, position: Int) {
        holder.bind(dataSentenceList[position], position)
    }

    override fun getItemCount(): Int = dataSentenceList.size

    fun setData(data: List<String>) {
        dataSentenceList.clear()
        dataSentenceList.addAll(data)
        notifyDataSetChanged()
    }
}