package kr.co.pureum.views.battle

import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityBattleAcceptBinding

@AndroidEntryPoint
class BattleAcceptActivity : BaseActivity<ActivityBattleAcceptBinding>(R.layout.activity_battle_accept) {
    override fun initView() {
        initListener()
    }

    private fun initListener() {
        with(binding) {
            battleAcceptWirteButton.setOnClickListener {
                val intent = Intent(this@BattleAcceptActivity, MyBattleWriteSentenceActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // TODO: battleId 받기 및 대기 중 대결 정보 반환 APi 연결, 라이브 데이터 뿌리기
}