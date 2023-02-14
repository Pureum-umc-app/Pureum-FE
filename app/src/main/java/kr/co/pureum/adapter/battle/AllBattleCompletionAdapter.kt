package kr.co.pureum.adapter.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.domain.model.AllBattleCompletionDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemBattleMyCompletionBinding

class AllBattleCompletionAdapter : RecyclerView.Adapter<AllBattleCompletionAdapter.ViewHolder>() {
    private lateinit var binding: ItemBattleMyCompletionBinding
    private var allBattleCompletionList = mutableListOf<AllBattleCompletionDto>()

    interface Listener {
        fun onItemClick(pos: Int, type: Int, itemId: Long)
    }

    private lateinit var completionListener : Listener

    fun setListener(listener: Listener){
        completionListener = listener
    }

    inner class ViewHolder(val binding: ItemBattleMyCompletionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(compData: AllBattleCompletionDto, position: Int) {
            with(binding) {
                myBattleKeywordComTv.text = compData.word
                myBattleWinnerName.text = compData.winnerNickname
                Glide.with(myBattleWinnerProfile.context)
                    .load(compData.winnerProfileImg)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(myBattleWinnerProfile)



                root.setOnClickListener {
                    completionListener.onItemClick(position, compData.hasResult, compData.battleId)
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