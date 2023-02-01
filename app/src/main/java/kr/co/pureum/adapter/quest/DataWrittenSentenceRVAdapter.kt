package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.DataWrittenSentence
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemWrittenSentenceBinding

class DataWrittenSentenceRVAdapter(private val dataWrittenSentenceList: ArrayList<DataWrittenSentence>) : RecyclerView.Adapter<DataWrittenSentenceRVAdapter.DataWrittenSentenceViewHolder>() {
    inner class DataWrittenSentenceViewHolder(private val binding: ItemWrittenSentenceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(dataWrittenSentence: DataWrittenSentence) {
            binding.itemProfileImageIb.setImageResource(R.drawable.ic_appicon_round)
            binding.itemUserNicknameTv.text = dataWrittenSentence.userNickname
            binding.itemUploadTimeTv.text = dataWrittenSentence.uploadTime
            binding.itemLikeNumberTv.text = dataWrittenSentence.likeNumber
            binding.itemWrittenSentenceTv.text = dataWrittenSentence.writtenSentence
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
        holder.bind(dataWrittenSentenceList[position])
    }

    override fun getItemCount(): Int = dataWrittenSentenceList.size
}