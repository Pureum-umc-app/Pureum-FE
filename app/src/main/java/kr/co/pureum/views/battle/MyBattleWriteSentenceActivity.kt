package kr.co.pureum.views.battle

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityMyBattleWriteSentenceBinding

@AndroidEntryPoint
class MyBattleWriteSentenceActivity : BaseActivity<ActivityMyBattleWriteSentenceBinding>(R.layout.activity_my_battle_write_sentence),
    View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_battle_write_sentence)
    }

    override fun initView() {
        initListener()
    }

    private fun initListener() {
        with(binding) {
            battleSentenceCompletionBt.setOnClickListener(this@MyBattleWriteSentenceActivity)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.battleSentenceCompletionBt.id -> {
                val dlg = BattleDialog(this)
                dlg.setOnClickedListener {

                }
                dlg.show()
            }
        }
    }
}