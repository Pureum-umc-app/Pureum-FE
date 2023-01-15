package kr.co.pureum.views.signup

import android.content.Intent
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityOnboardBinding

class OnboardActivity : BaseActivity<ActivityOnboardBinding>(R.layout.activity_onboard) {

    override fun initView() {

        binding.onboardNext.setOnClickListener {

            val intent = Intent(this, OnboardTwoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)

            finish()
            overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity)
        }

    }
}