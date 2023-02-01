package kr.co.pureum.adapter.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.DataWrittenSentence
import kr.co.pureum.databinding.ItemMySentenceBinding

class DataMySentenceRVAdapter(private val dataMySentenceList: ArrayList<DataWrittenSentence>) : RecyclerView.Adapter<DataMySentenceRVAdapter.DataMySentenceViewHolder>() {
    inner class DataMySentenceViewHolder(private val binding: ItemMySentenceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMySentence: DataWrittenSentence) {
            binding.itemMyKeyword.text = dataMySentence.todayKeyword
            binding.itemMySentence.text = dataMySentence.writtenSentence
            binding.itemMyLikeNumber.text = dataMySentence.likeNumber
            binding.itemPrivatePublic.text = dataMySentence.type
        }

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataMySentenceViewHolder {
        val binding = ItemMySentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataMySentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataMySentenceViewHolder, position: Int) {
        holder.bind(dataMySentenceList[position])
    }

    override fun getItemCount(): Int  = dataMySentenceList.size
}