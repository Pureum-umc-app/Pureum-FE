package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.DataSentence
import kr.co.pureum.databinding.ItemSentenceClikcBinding

class DataTodaySentenceRVAdapter(private val dataSentenceClickList: ArrayList<DataSentence>) : RecyclerView.Adapter<DataTodaySentenceRVAdapter.DataTodaySentenceViewHolder>() {
    inner class DataTodaySentenceViewHolder(private val binding: ItemSentenceClikcBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataSentence: DataSentence) {
            binding.questTodayTv.text = dataSentence.today
            binding.questSentenceTv.text = dataSentence.sentence
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataTodaySentenceViewHolder {
        val binding = ItemSentenceClikcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataTodaySentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataTodaySentenceViewHolder, position: Int) {
        holder.bind(dataSentenceClickList[position])
    }

    override fun getItemCount(): Int = dataSentenceClickList.size
}