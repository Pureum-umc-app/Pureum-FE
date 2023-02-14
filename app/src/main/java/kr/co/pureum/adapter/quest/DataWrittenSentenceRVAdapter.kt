package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.SentencesListDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemWrittenSentenceBinding

class DataWrittenSentenceRVAdapter() : RecyclerView.Adapter<DataWrittenSentenceRVAdapter.DataWrittenSentenceViewHolder>() {
    private var writtenSentenceList = mutableListOf<SentencesListDto>()

    inner class DataWrittenSentenceViewHolder(private val binding: ItemWrittenSentenceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(sentencesListDto: SentencesListDto) {
            with(binding) {
                itemProfileImageIb.setImageResource(R.drawable.ic_appicon_round)
                writtenSentence = sentencesListDto
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataWrittenSentenceViewHolder {
        val binding = ItemWrittenSentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataWrittenSentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataWrittenSentenceViewHolder, position: Int) {
        holder.bind(writtenSentenceList[position])
    }

    override fun getItemCount(): Int = writtenSentenceList.size

    fun setData(data: List<SentencesListDto>) {
        writtenSentenceList.clear()
        writtenSentenceList.addAll(data)
        notifyDataSetChanged()
    }
}