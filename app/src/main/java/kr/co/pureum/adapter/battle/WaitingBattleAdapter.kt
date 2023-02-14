package kr.co.pureum.adapter.battle

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.domain.model.WaitingBattle
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemWaitingBattleBinding

class WaitingBattleAdapter(
    private val context: Context,
) : RecyclerView.Adapter<WaitingBattleAdapter.ViewHolder>() {
    private lateinit var binding: ItemWaitingBattleBinding
    private var battleList = mutableListOf<WaitingBattle>()

    inner class ViewHolder(val binding: ItemWaitingBattleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(battle: WaitingBattle, position: Int){
            with(binding) {
                waitingBattle = battle
                Glide.with(context)
                    .load(battle.otherProfileImg)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(otherProfileImage)
            }
        }
    }

    override fun getItemCount(): Int = battleList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_waiting_battle, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(battleList[position], position)
    }

    fun setData(data: List<WaitingBattle>) {
        battleList.clear()
        battleList.addAll(data)
        notifyItemRangeInserted(battleList.size, 5)
    }
}