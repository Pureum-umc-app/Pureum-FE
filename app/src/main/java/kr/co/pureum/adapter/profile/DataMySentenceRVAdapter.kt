package kr.co.pureum.adapter.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.MySentenceList
import kr.co.pureum.databinding.ItemMySentenceBinding

class DataMySentenceRVAdapter(private var optionsMenuClickListener: OptionsMenuClickListener) : RecyclerView.Adapter<DataMySentenceRVAdapter.DataMySentenceViewHolder>() {
    private var mySentenceList = mutableListOf<MySentenceList>()
    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int)
    }
    inner class DataMySentenceViewHolder(val binding: ItemMySentenceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMySentence: MySentenceList) {
            binding.itemMyKeyword.text = dataMySentence.word
            binding.itemMySentence.text = dataMySentence.sentence
            binding.itemMyLikeNumber.text = dataMySentence.countLike.toString()
            binding.itemPrivatePublic.text = dataMySentence.status
        }

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataMySentenceViewHolder {
        val binding = ItemMySentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataMySentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataMySentenceViewHolder, position: Int) {
        with(holder) {
            bind(mySentenceList[position])
            binding.itemDotsButton.setOnClickListener {
                optionsMenuClickListener.onOptionsMenuClicked(position)
            }
        }
    }

    override fun getItemCount(): Int  = mySentenceList.size

    fun setData(data: List<MySentenceList>) {
        mySentenceList.clear()
        mySentenceList.addAll(data)
        notifyDataSetChanged()
    }
}