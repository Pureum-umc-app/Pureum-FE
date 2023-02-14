package kr.co.pureum.adapter.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.domain.model.MyBattleCompletionDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemBattleMyCompletionBinding


class MyBattleCompletionAdapter : RecyclerView.Adapter<MyBattleCompletionAdapter.ViewHolder>() {
    private lateinit var binding: ItemBattleMyCompletionBinding
    private var myBattleCompletionList = mutableListOf<MyBattleCompletionDto>()

    interface Listener {
        fun onItemClick(pos: Int, type: Int)
    }

    private lateinit var completionListener : Listener

    fun setListener(listener: Listener){
        completionListener = listener
    }

    inner class ViewHolder(val binding: ItemBattleMyCompletionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(compData: MyBattleCompletionDto, position: Int) {
            with(binding) {
                myBattleKeywordComTv.text = compData.word
                myBattleWinnerName.text = compData.winnerNickname
                Glide.with(myBattleWinnerProfile.context)
                    .load(compData.winnerProfileImg)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(myBattleWinnerProfile)

                root.setOnClickListener {
                    completionListener.onItemClick(position, compData.situation)
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