package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.DataSentence
import kr.co.pureum.databinding.ItemSentenceBinding

class DataSentenceRVAdapter(private var dataSentenceList: ArrayList<DataSentence>) : RecyclerView.Adapter<DataSentenceRVAdapter.DataSentenceViewHolder>() {

    inner class DataSentenceViewHolder(private val binding: ItemSentenceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataSentence: DataSentence, position: Int) {
            binding.keyword = dataSentence.sentence
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

    fun setData(data: ArrayList<DataSentence>) {
        dataSentenceList.clear()
        dataSentenceList.addAll(data)
        notifyDataSetChanged()
    }
}