package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.DataSentence
import kr.co.pureum.databinding.ItemSentenceBinding

class DataSentenceRVAdapter(private val dataSentenceList: ArrayList<DataSentence>) : RecyclerView.Adapter<DataSentenceRVAdapter.DataSentenceViewHolder>() {

    inner class DataSentenceViewHolder(private val binding: ItemSentenceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataSentence: DataSentence) {
            binding.itemKeywordTv.text = dataSentence.sentence
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataSentenceViewHolder {
        val binding = ItemSentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataSentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataSentenceViewHolder, position: Int) {
        holder.bind(dataSentenceList[position])
    }

    override fun getItemCount(): Int = dataSentenceList.size
}