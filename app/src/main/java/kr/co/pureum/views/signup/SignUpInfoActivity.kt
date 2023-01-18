package kr.co.pureum.views.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpInfoBinding

class SignUpInfoActivity : BaseActivity<ActivitySignUpInfoBinding>(R.layout.activity_sign_up_info) {

    override fun initView() {

        binding.signupClauseInfoBackBt.setOnClickListener{

            finish()
            overridePendingTransition(R.anim.not_move_activity, R.anim.rightout_activity)
        }

        binding.clauseAgreeOkBt.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.not_move_activity, R.anim.rightout_activity)
        }

    }
}

