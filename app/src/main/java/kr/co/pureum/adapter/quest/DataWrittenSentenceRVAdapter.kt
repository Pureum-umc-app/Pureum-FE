package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.SentencesListDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemWrittenSentenceBinding

class DataWrittenSentenceRVAdapter(
    private val clickListener: OnDataWrittenSentenceClickListener
) : RecyclerView.Adapter<DataWrittenSentenceRVAdapter.DataWrittenSentenceViewHolder>() {
    private var writtenSentenceList = mutableListOf<SentencesListDto>()
    private lateinit var binding: ItemWrittenSentenceBinding

    interface OnDataWrittenSentenceClickListener {
        fun onBlameClickListener(sentenceId : Long, isBlamed: String)
    }

    inner class DataWrittenSentenceViewHolder(binding: ItemWrittenSentenceBinding) : RecyclerView.ViewHolder(binding.root){
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
        binding = ItemWrittenSentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataWrittenSentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataWrittenSentenceViewHolder, position: Int) {
        holder.bind(writtenSentenceList[position])
        binding.itemWrittenSentenceBlameTv.setOnClickListener {
            clickListener.onBlameClickListener(writtenSentenceList[position].sentenceId, writtenSentenceList[position].isBlamed)
        }
    }

    override fun getItemCount(): Int = writtenSentenceList.size

    fun setData(data: List<SentencesListDto>) {
        writtenSentenceList.clear()
        writtenSentenceList.addAll(data)
        notifyDataSetChanged()
    }
    fun updateItem(sentenceId: Long, newBlameState: String) {
        val index = writtenSentenceList.indexOfFirst { it.sentenceId == sentenceId }
        if (index != -1) {
            writtenSentenceList[index].isBlamed = newBlameState
            notifyItemChanged(index)
        }
    }
}