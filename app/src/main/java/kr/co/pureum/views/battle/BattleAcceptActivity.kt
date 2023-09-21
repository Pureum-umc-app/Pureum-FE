package kr.co.pureum.views.battle

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityBattleAcceptBinding

@AndroidEntryPoint
class BattleAcceptActivity : BaseActivity<ActivityBattleAcceptBinding>(R.layout.activity_battle_accept) {
    private val viewModel by viewModels<MyBattleProgInfoViewModel>()
    private var battleId: Long? = null
    override fun initView() {
        battleId = intent.getLongExtra("battleId", 0)

        initListener()
        Log.e("battleId", battleId.toString())
        viewModel.getMyWaitBattleInfo(battleId!!)
        observe()
    }

    private fun initListener() {
        with(binding) {
            battleAcceptWirteButton.setOnClickListener {
                val intent = Intent(this@BattleAcceptActivity, MyBattleWriteSentenceActivity::class.java).apply {
                    putExtra("battleId", battleId)
                }
                startActivity(intent)
            }
        }
    }

    private fun observe() {
        viewModel.myWaitBattleInfoLiveData.observe(this) {
            binding.myWaitBattle = it
            binding.isLoading = false
        }
    }
    // TODO: 대기 중 대결 정보 반환 APi 연결, 라이브 데이터 뿌리기
}