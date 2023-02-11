package kr.co.pureum.views.signup

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpProfileBinding
import kr.co.pureum.utils.UriParser
import java.io.File

@AndroidEntryPoint
class SignUpProfileActivity : BaseActivity<ActivitySignUpProfileBinding>(R.layout.activity_sign_up_profile) {
    private val viewModel by viewModels<OnBoardViewModel>()

    companion object{
        private val REQUIRED_EXTERNAL_STORAGE_PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    var imageFile : File? = null
    var nickname: String = ""

    // 사진 받기
    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    // 저장소 권한
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
        isGranted.forEach {
            if (it.value){
                Log.e(TAG, "외부 저장소 읽기")
            } else {
                showToast("권한 동의 후 다시 한번 클릭해주세요.")
            }
        }
    }

    private fun showDialog(imgUri: Uri?){
        imgUri?.let {
            binding.signupProfileImage.load(imgUri) {
                transformations(RoundedCornersTransformation(10F, 10F, 10F, 10F))
            }
            imageFile = File(UriParser().getPath(this@SignUpProfileActivity, imgUri).toString())
            Log.e(TAG, "${imageFile!!.name}, $imageFile, $imgUri")
        }
    }

    private fun openStorage() {
        activityResultLauncher.launch(
            Intent(Intent.ACTION_PICK).apply {
                setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.CONTENT_TYPE)
                type = "image/*"
            }
        )
    }

    override fun initView() {
        initListener()
        checkNickname()
        observe()

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK){
                showDialog(it.data?.data)
            }
        }
    }

    private fun initListener() {
        with(binding) {
            // 앨범 버튼 클릭 리스너 구현
            var agree = false
            signupChangeProfileButton.setOnClickListener{
                if (!agree){
                    requestPermissionLauncher.launch(REQUIRED_EXTERNAL_STORAGE_PERMISSIONS)
                    AccessDialog().apply {
                        setCustomListener(object : AccessDialog.AccessDialogListener {
                            override fun onConfirm() {
                                agree = true
                            }
                        })
                        show(supportFragmentManager, "이미지 접근 권한 허용")
                    }
                } else {
                    openStorage()
                }
            }
            signupNicknameEditText.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                    signupAgreeNextBt.performClick()
                }
                true
            }
            signupAgreeNextBt.setOnClickListener {
                if(signupNicknameTextLayout.error == null || signupNicknameTextLayout.error == ""){
                    nickname = signupNicknameEditText.text.toString().trim()
                    viewModel.nicknameValidation(nickname)
                }
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

    private fun observe() {
        viewModel.nicknameValidationLiveData.observe(this) {
            when (it) {
                "유효한 닉네임입니다." -> {
                    startActivity(Intent(this, SignUpGradeActivity::class.java).apply {
                        putExtra("kakaoToken", intent.getStringExtra("kakaoToken"))
                        putExtra("imageFile", imageFile.toString())
                        putExtra("nickname", nickname)
                    })
                    this.overridePendingTransition(R.anim.rightin_activity, R.anim.not_move_activity)
                }
                else -> {
                    showToast(it)
                    binding.signupAgreeNextBt.isEnabled = false
                }
            }
        }
    }
}