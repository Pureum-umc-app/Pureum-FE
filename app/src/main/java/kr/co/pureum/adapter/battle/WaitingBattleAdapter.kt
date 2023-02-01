package kr.co.pureum.adapter.battle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.domain.model.WaitingBattleDto
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemWaitingBattleBinding

class WaitingBattleAdapter : RecyclerView.Adapter<WaitingBattleAdapter.ViewHolder>() {
    private lateinit var binding: ItemWaitingBattleBinding
    private var battleList = mutableListOf<WaitingBattleDto>()

    inner class ViewHolder(val binding: ItemWaitingBattleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(waitingBattleDto: WaitingBattleDto, position: Int){
            with(binding) {
                waitingBattle = waitingBattleDto
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

    fun setData(data: List<WaitingBattleDto>) {
        battleList.clear()
        battleList.addAll(data)
        notifyDataSetChanged()
    }
}