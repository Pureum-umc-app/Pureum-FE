package kr.co.pureum.adapter.battle

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.domain.model.Opponent
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemBattleOpponentBinding

class BattleOpponentAdapter(
    private val context : Context
): RecyclerView.Adapter<BattleOpponentAdapter.ViewHolder>() {
    private lateinit var binding: ItemBattleOpponentBinding
    private var opponentList = mutableListOf<Opponent>()
    private var checkedIndex = -1

    interface Listener {
        fun onClick(checkedIndex: Int)
    }

    private lateinit var opponentListener : Listener

    fun setListener(listener: Listener){
        opponentListener = listener
    }

    inner class ViewHolder(val binding: ItemBattleOpponentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(opponent: Opponent, position: Int){
            with(binding) {
                nickname = opponent.nickname
                isChecked = position == checkedIndex
                Glide.with(context)
                    .load(opponent.image)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(battleOpponentProfileImage)
                battleOpponentCheckBox.setOnClickListener {
                    val temp = checkedIndex
                    if (position == checkedIndex) {
                        checkedIndex = -1
                    } else {
                        checkedIndex = position
                        notifyItemChanged(checkedIndex)
                    }
                    opponentListener.onClick(checkedIndex)
                    notifyItemChanged(temp)
                }
            }
        }
    }

    override fun getItemCount(): Int = opponentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_battle_opponent, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(opponentList[position], position)
    }

    fun setData(data: List<Opponent>) {
        opponentList.clear()
        opponentList.addAll(data)
        notifyItemRangeInserted(opponentList.size, 10)
    }
}