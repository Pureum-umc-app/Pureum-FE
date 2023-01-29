package kr.co.pureum.views.signup

import android.Manifest
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.RoundedCornersTransformation
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpProfileBinding
import java.util.logging.Logger


class SignUpProfileActivity : BaseActivity<ActivitySignUpProfileBinding>(R.layout.activity_sign_up_profile) {

    var agree = 0

    override fun initView() {

        checkNickname()
        profileImgToAlbum()


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

                            if(signupNicknameTf.error==null || signupNicknameTf.error==""){
                                val intent = Intent(this@SignUpProfileActivity, SignUpGradeActivity::class.java)
                                startActivity(intent)
                                this@SignUpProfileActivity.overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)
                            }

                        }
                    }
                }




            })

        }
    }


    fun profileImgToAlbum(){

        with(binding) {

            val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                result.forEach {
                    if(!it.value) {
//                            Toast.makeText(applicationContext, "권한 동의 필요!", Toast.LENGTH_SHORT).show()
//                        finish()
                    }
                }
            }
            val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                signupBasicProfileIb.load(uri){
                    transformations(RoundedCornersTransformation(10F,10F,10F,10F))
                }
            }

            checkPermission.launch(permissionList)

            // 앨범 버튼 클릭 리스너 구현
            signupChangeImgCl.setOnClickListener{

                if(agree == 0){
                    val dlg = AccessDialog(this@SignUpProfileActivity)
                    dlg.show()
                    agree++
                }
                else{
                    readImage.launch("image/*")
                }

            }
        }
    }


}