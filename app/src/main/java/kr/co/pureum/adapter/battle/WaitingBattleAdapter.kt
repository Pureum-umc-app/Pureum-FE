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

    interface Listener {
        fun onClickRefuseButton(battleId: Long)
        fun onClickAcceptButton(battleId: Long)
        fun onClickCancelButton(battleId: Long)
    }

    private lateinit var waitingBattleListener : Listener

    fun setListener(listener: Listener){
        waitingBattleListener = listener
    }

    inner class ViewHolder(val binding: ItemWaitingBattleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(battle: WaitingBattle, position: Int){
            with(binding) {
                waitingBattle = battle
                Glide.with(context)
                    .load(battle.otherProfileImg)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(otherProfileImage)

                var touched = false
                isTouched = touched
                waitingBattleLayout.setOnClickListener {
                    touched = !touched
                    isTouched = touched
                }

                // 상태 메세지에 따른 동작 처리
                when(battle.status) {
                    "대결장이 도착했어요!" -> { /* 상대가 보낸 요청, 내가 아직 수락하지 않은 경우 */ isMine = false }
                    "대결 문장을 작성해주세요!" -> { /* 상대가 보낸 요청, 내가 수락하고 문장을 작성하지 않은 경우 */ isMine = false }
                    "대결 수락 대기 중" -> { /* 내가 보낸 요청, 상대가 아직 수락하지 않은 경우 */ isMine = true }
                    "대결 문장 작성 대기 중" -> { /* 내가 보낸 요청, 상대가 수락하고 문장을 작성하지 않은 경우 */ isMine = true }
                    else -> { /* 오류 발생 */ }
                }

                with(waitingBattleListener) {
                    waitingBattleRefuseButton.setOnClickListener { onClickRefuseButton(battle.battleId) }
                    waitingBattleAcceptButton.setOnClickListener { onClickAcceptButton(battle.battleId) }
                    waitingBattleCancelButton.setOnClickListener { onClickCancelButton(battle.battleId) }
                }
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
        notifyDataSetChanged()
    }
}