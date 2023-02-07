package kr.co.pureum.adapter.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.MyBattleProgressDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemBattleMyProgressBinding

class MyBattleProgressAdapter : RecyclerView.Adapter<MyBattleProgressAdapter.ViewHolder>() {
    private lateinit var binding: ItemBattleMyProgressBinding
    private var myBattleProgressList = mutableListOf<MyBattleProgressDto>()

    interface Listener {
        fun onItemClick(pos: Int)
    }

    private lateinit var progressListener : Listener

    fun setListener(listener: Listener){
        progressListener = listener
    }

    inner class ViewHolder(val binding: ItemBattleMyProgressBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(progressData: MyBattleProgressDto, position: Int) {
            with(binding) {
                myBattleKeywordTv.text = progressData.keyword
                myBattleDay.text = progressData.day
                myBattleFirstNameTv.text = progressData.firstUserName
//                myBattleFirstProfileIv.setImageResource(progressData.firstUserProfile.toInt())
                myBattleFirstLikeIv.setImageResource(R.drawable.ic_battle_heart_not_fill)
                myBattleFirstLikeNum.text = progressData.firstLikeNum.toString()
                myBattleSecondLikeIv.setImageResource(R.drawable.ic_battle_heart_fill)
                myBattleSecondLikeNum.text = progressData.secondLikeNum.toString()
                myBattleSecondNameTv.text = progressData.secondUserName
//                myBattleSecondProfileIv.setImageResource(progressData.secondUserProfile.toInt())

                root.setOnClickListener {
                    progressListener.onItemClick(position)
                }
            }
        }
    }


    override fun getItemCount(): Int = myBattleProgressList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_battle_my_progress, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myBattleProgressList[position], position)
    }

    fun setData(data: List<MyBattleProgressDto>) {
        myBattleProgressList.clear()
        myBattleProgressList.addAll(data)
        notifyDataSetChanged()
    }
}