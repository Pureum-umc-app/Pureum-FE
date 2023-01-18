package kr.co.pureum.views.signup

import android.view.View
import android.widget.Button
import android.widget.EditText
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpProfileBinding

class SignUpProfileActivity : BaseActivity<ActivitySignUpProfileBinding>(R.layout.activity_sign_up_profile) {

    override fun initView() {

        val nameEt = binding.signupNicknameEt as EditText
        nameEt.setBackgroundResource(R.drawable.signup_edittext_round)

    }
}