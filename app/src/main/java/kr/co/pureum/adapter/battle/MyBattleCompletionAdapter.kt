package kr.co.pureum.adapter.battle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemBattleMyCompletionBinding


class MyBattleCompletionAdapter : RecyclerView.Adapter<MyBattleCompletionAdapter.ViewHolder>() {
    private lateinit var binding: ItemBattleMyCompletionBinding
    private var myBattleCompletionList = mutableListOf<MyBattleCompletionDto>()

    interface Listener {
        fun onItemClick(pos: Int)
    }

    private lateinit var completionListener : Listener

    fun setListener(listener: Listener){
        completionListener = listener
    }

    inner class ViewHolder(val binding: ItemBattleMyCompletionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(progressData: MyBattleCompletionDto, position: Int) {
            with(binding) {
                myBattleKeywordComTv.text = progressData.keyword.toString()
                myBattleWinnerName.text = progressData.winnerNickname.toString()
                myBattleWinnerProfile.setImageResource(R.drawable.ic_battle_basic_profile)

                root.setOnClickListener {
                    completionListener.onItemClick(position)
                }
            }
        }
    }


    override fun getItemCount(): Int = myBattleCompletionList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_battle_my_completion, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myBattleCompletionList[position], position)
    }

    fun setData(data: List<MyBattleCompletionDto>) {
        myBattleCompletionList.clear()
        myBattleCompletionList.addAll(data)
        notifyDataSetChanged()
    }

}