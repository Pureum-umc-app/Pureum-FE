package kr.co.pureum.views.signup

import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpClauseBinding
import kr.co.pureum.views.MainActivity

class SignUpClauseActivity : BaseActivity<ActivitySignUpClauseBinding>(R.layout.activity_sign_up_clause) {

    override fun initView() {

        binding.signupClauseBackBt.setOnClickListener{

            finish()
            overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity)
        }

        binding.clauseAgreeOkBt.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity)
        }

    }

}