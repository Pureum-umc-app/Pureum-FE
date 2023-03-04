package kr.co.pureum.adapter.profile

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.MySentenceList
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemMySentenceBinding

class DataMySentenceRVAdapter(private var optionsMenuClickListener: OptionsMenuClickListener, private val context: Context) : RecyclerView.Adapter<DataMySentenceRVAdapter.DataMySentenceViewHolder>() {
    private var mySentenceList = mutableListOf<MySentenceList>()
    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(sentenceId: Long, position: Int)
        fun modifyClicked(sentenceId: Long)
        fun deleteClicked(sentenceId: Long)
    }

    inner class DataMySentenceViewHolder(val binding: ItemMySentenceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMySentence: MySentenceList, position: Int) {
            binding.itemMyKeyword.text = dataMySentence.word
            binding.itemMySentence.text = dataMySentence.sentence
            binding.itemMyLikeNumber.text = dataMySentence.countLike.toString()
            if (dataMySentence.status == "P")
                binding.itemPrivatePublic.text = "비공개"
            else if (dataMySentence.status == "O")
                binding.itemPrivatePublic.text = "공개"
            binding.itemDotsButton.setOnClickListener {
                optionsMenuClickListener.onOptionsMenuClicked(dataMySentence.sentenceId, position)
                val popupMenu = PopupMenu(context, binding.itemDotsButton)
                popupMenu.inflate(R.menu.menu_my_sentences)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item?.itemId) {
                        R.id.menu_modify -> {
                            Toast.makeText(context, "item_modify_clicked", Toast.LENGTH_SHORT).show()
                            optionsMenuClickListener.modifyClicked(dataMySentence.sentenceId)
                        }
                        R.id.menu_delete -> {
                            Toast.makeText(context, "item_delete_clicked", Toast.LENGTH_SHORT).show()
                            optionsMenuClickListener.deleteClicked(dataMySentence.sentenceId)
                        }
                    }
                    false
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true)
                }
                popupMenu.show()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataMySentenceViewHolder {
        val binding = ItemMySentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataMySentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataMySentenceViewHolder, position: Int) {
        with(holder) {
            bind(mySentenceList[position], position)
        }
    }

    override fun getItemCount(): Int  = mySentenceList.size

    fun setData(data: List<MySentenceList>) {
        mySentenceList.clear()
        mySentenceList.addAll(data)
        notifyDataSetChanged()
    }
}