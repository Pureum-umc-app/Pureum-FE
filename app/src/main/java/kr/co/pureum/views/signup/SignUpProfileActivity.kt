package kr.co.pureum.views.signup

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpProfileBinding
import java.util.logging.Logger


class SignUpProfileActivity : BaseActivity<ActivitySignUpProfileBinding>(R.layout.activity_sign_up_profile) {

    override fun initView() {

//        val nameEt = binding.signupNicknameEt as EditText
//        nameEt.setBackgroundResource(R.drawable.signup_edittext_round)

        checkNickname()

    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

    fun buttonOnOff(flag: Int){
        with(binding) {

            if(flag == 0){
                signupAgreeNextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(216,236,255))
                signupAgreeNextBt.setTextColor(Color.parseColor("#6E6D73"))
            }

            else if(flag == 1){
                signupAgreeNextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(133,181,255))
                signupAgreeNextBt.setTextColor(Color.parseColor("#FFFFFF"))
            }

        }

    }

    private fun checkNickname(){
        with(binding) {
            signupNicknameEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        signupNicknameTf.error = "공백은 허용하지 않습니다."
                        buttonOnOff(0)

                    } else if (s.toString().length > 10){
                        signupNicknameTf.error = "닉네임은 최대 10자까지 입력 가능합니다."
                        buttonOnOff(0)
                    }
                    else{
                        signupNicknameTf.error = null
                        buttonOnOff(1)
                        signupAgreeNextBt.setOnClickListener {
//                            finish()
                            val intent = Intent(this@SignUpProfileActivity, SignUpGradeActivity::class.java)
                            startActivity(intent)
                            this@SignUpProfileActivity.overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)
                        }
                    }
                }


            })
        }
    }

    fun profileImgToAlbum(){
        with(binding) {



            // 앨범 버튼 클릭 리스너 구현
            signupBasicProfileIb.setOnClickListener{
//                requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_Album)

            }
        }
    }




}