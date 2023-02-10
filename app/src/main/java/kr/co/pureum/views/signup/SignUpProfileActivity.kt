package kr.co.pureum.views.signup

import android.Manifest
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpProfileBinding
import java.util.logging.Logger

@AndroidEntryPoint
class SignUpProfileActivity : BaseActivity<ActivitySignUpProfileBinding>(R.layout.activity_sign_up_profile) {
    private val viewModel by viewModels<OnBoardViewModel>()
    var agree = 0

    override fun initView() {
        initListener()
        checkNickname()
        profileImgToAlbum()
        observe()
    }

    private fun initListener() {
        with(binding) {
            signupAgreeNextBt.setOnClickListener {
                if(signupNicknameTextLayout.error == null || signupNicknameTextLayout.error == ""){
                    viewModel.nicknameValidation(signupNicknameEditText.text.toString().trim())
                }
            }
            signupNicknameEditText.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                    signupAgreeNextBt.performClick()
                }
                true
            }
        }
    }

    private fun checkNickname(){
        with(binding) {
            signupNicknameEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        signupNicknameTextLayout.error = "공백은 허용하지 않습니다."
                        signupAgreeNextBt.isEnabled = false

                    } else if (s.toString().length > 10){
                        signupNicknameTextLayout.error = "닉네임은 최대 10자까지 입력 가능합니다."
                        signupAgreeNextBt.isEnabled = false
                    }
                    else {
                        signupNicknameTextLayout.error = null
                        signupAgreeNextBt.isEnabled = true
                    }
                }
            })
        }
    }

    private fun profileImgToAlbum(){
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
                signupProfileImage.load(uri){
                    transformations(RoundedCornersTransformation(10F,10F,10F,10F))
                }
            }

            checkPermission.launch(permissionList)

            // 앨범 버튼 클릭 리스너 구현
            signupChangeProfileButton.setOnClickListener{
                if(agree == 0){
                    val dlg = AccessDialog(this@SignUpProfileActivity)
                    dlg.show()
                    agree++
                } else {
                    readImage.launch("image/*")
                }
            }
        }
    }

    private fun observe() {
        viewModel.nicknameValidationLiveData.observe(this) {
            when (it) {
                "유효한 닉네임입니다." -> {
                    val intent = Intent(this, SignUpGradeActivity::class.java)
                    startActivity(intent)
                    this.overridePendingTransition(R.anim.rightin_activity, R.anim.not_move_activity)
                }
                else -> {
                    binding.signupNicknameTextLayout.error = it
                    binding.signupAgreeNextBt.isEnabled = false
                }
            }
        }
    }
}