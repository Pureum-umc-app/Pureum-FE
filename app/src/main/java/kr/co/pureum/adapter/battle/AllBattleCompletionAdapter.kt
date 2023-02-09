package kr.co.pureum.adapter.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.AllBattleCompletionDto
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemBattleMyCompletionBinding

class AllBattleCompletionAdapter : RecyclerView.Adapter<AllBattleCompletionAdapter.ViewHolder>() {
    private lateinit var binding: ItemBattleMyCompletionBinding
    private var allBattleCompletionList = mutableListOf<AllBattleCompletionDto>()

    interface Listener {
        fun onItemClick(pos: Int, type: Int)
    }

    private lateinit var completionListener : Listener

    fun setListener(listener: Listener){
        completionListener = listener
    }

    inner class ViewHolder(val binding: ItemBattleMyCompletionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(compData: AllBattleCompletionDto, position: Int) {
            with(binding) {
                myBattleKeywordComTv.text = compData.word
                myBattleWinnerName.text = compData.winnerNickname.toString()
                myBattleWinnerProfile.setImageResource(R.drawable.ic_battle_basic_profile)


                root.setOnClickListener {
                    completionListener.onItemClick(position, compData.type)
                }
            }

        }
    }


    override fun getItemCount(): Int = allBattleCompletionList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_battle_my_completion, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allBattleCompletionList[position], position)
    }

    fun setData(data: List<AllBattleCompletionDto>) {
        allBattleCompletionList.clear()
        allBattleCompletionList.addAll(data)
        notifyDataSetChanged()
    }

}