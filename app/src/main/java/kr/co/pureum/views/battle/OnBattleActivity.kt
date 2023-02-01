package kr.co.pureum.views.battle

import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityOnBattleBinding

@AndroidEntryPoint
class OnBattleActivity : BaseActivity<ActivityOnBattleBinding>(R.layout.activity_on_battle) {
    override fun initView() {
        initToolbar()
        supportFragmentManager.beginTransaction().add(R.id.battle_frame, OnBattleFirstFragment()).commit()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener { finish() }
        }
    }
}