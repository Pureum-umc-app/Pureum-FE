package kr.co.pureum.views.signup

import android.content.Intent
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityOnboardTwoBinding

class OnboardTwoActivity : BaseActivity<ActivityOnboardTwoBinding>(R.layout.activity_onboard_two) {

    override fun initView() {

        binding.onboardNext.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)

            finish()
            overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity)
        }

    }
}