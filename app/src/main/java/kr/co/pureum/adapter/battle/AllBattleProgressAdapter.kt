package kr.co.pureum.adapter.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.domain.model.AllBattleProgressDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemBattleMyProgressBinding

class AllBattleProgressAdapter : RecyclerView.Adapter<AllBattleProgressAdapter.ViewHolder>() {
    private lateinit var binding: ItemBattleMyProgressBinding
    private var allBattleProgressList = mutableListOf<AllBattleProgressDto>()

    interface Listener {
        fun onItemClick(pos: Int, itemIdx: Long)
    }

    private lateinit var progressListener : Listener

    fun setListener(listener: Listener){
        progressListener = listener
    }

    inner class ViewHolder(val binding: ItemBattleMyProgressBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(progressData: AllBattleProgressDto, position: Int) {
            with(binding) {
                myBattleKeywordTv.text = progressData.keyword
                myBattleDay.text = progressData.duration
                myBattleFirstNameTv.text = progressData.challengerNickname
                Glide.with(myBattleFirstProfileIv.context)
                    .load(progressData.challengerProfileImg)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(myBattleFirstProfileIv)
                myBattleFirstLikeNum.text = progressData.challengerLikeCnt.toString()
                myBattleSecondLikeNum.text = progressData.challengedLikeCnt.toString()
                myBattleSecondNameTv.text = progressData.challengedNickname
                Glide.with(myBattleSecondProfileIv.context)
                    .load(progressData.challengedProfileImg)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(myBattleSecondProfileIv)

                when (progressData.isChallengerLike) {
                    1 -> {
                        myBattleFirstLikeIv.setImageResource(R.drawable.ic_battle_heart_fill)
                    }
                    0 -> {
                        myBattleFirstLikeIv.setImageResource(R.drawable.ic_battle_heart_not_fill)
                    }
                    else -> {
                        myBattleFirstLikeIv.setImageResource(R.drawable.ic_battle_heart_not_fill)
                    }
                }

                when (progressData.isChallengedLike) {
                    1 -> {
                        myBattleSecondLikeIv.setImageResource(R.drawable.ic_battle_heart_fill)
                    }
                    0 -> {
                        myBattleSecondLikeIv.setImageResource(R.drawable.ic_battle_heart_not_fill)
                    }
                    else -> {
                        myBattleSecondLikeIv.setImageResource(R.drawable.ic_battle_heart_not_fill)
                    }
                }

                root.setOnClickListener {
                    progressListener.onItemClick(position, progressData.battleId)
                }
            }
        }
    }


    override fun getItemCount(): Int = allBattleProgressList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_battle_my_progress, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allBattleProgressList[position], position)
    }

    fun setData(data: List<AllBattleProgressDto>) {
        allBattleProgressList.clear()
        allBattleProgressList.addAll(data)
        notifyDataSetChanged()
    }
}