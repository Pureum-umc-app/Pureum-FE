package kr.co.pureum.views.profile

import android.Manifest
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityProfileSettingBinding
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.di.PureumApplication
import kr.co.pureum.views.MainActivity
import kr.co.pureum.views.signup.AccessDialog
import kr.co.pureum.views.signup.SignUpGradeActivity

@AndroidEntryPoint
class ProfileSettingActivity : BaseActivity<ActivityProfileSettingBinding>(R.layout.activity_profile_setting) {
    private val viewModel by viewModels<ProfileViewModel>()

    companion object{
        private val REQUIRED_EXTERNAL_STORAGE_PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    var imageUri: Uri? = null
    var nickname: String = ""

    // 사진 받기
    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    // 저장소 권한
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { }

    private fun extractImagePath(imgUri: Uri?){
        imgUri?.let {
            imageUri = it
            Glide.with(this)
                .load(it)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.profileImage)
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
        with(binding) {
            userNickname = intent.getStringExtra("nickname")
            Glide.with(this@ProfileSettingActivity)
                .load(intent.getStringExtra("profileUrl"))
                .transform(CenterCrop(), RoundedCorners(10))
                .into(profileImage)
        }

        initToolbar()
        initListener()
        checkNickname()
        observe()

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK){
                extractImagePath(it.data?.data)
            }
        }
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener { finish() }
        }
    }

    private fun initListener() {
        with(binding) {
            // 앨범 버튼 클릭 리스너 구현
            var agree = false
            profileImageEditButton.setOnClickListener{
                if (!agree){
                    requestPermissionLauncher.launch(REQUIRED_EXTERNAL_STORAGE_PERMISSIONS)
                    AccessDialog().apply {
                        setCustomListener(object : AccessDialog.AccessDialogListener {
                            override fun onConfirm() {
                                agree = true
                                profileImageEditButton.performClick()
                            }
                        })
                        show(supportFragmentManager, "이미지 접근 권한 허용")
                    }
                } else {
                    openStorage()
                }
            }
            profileSettingFinishButton.setOnClickListener {
                if(profileNicknameEditText.error == null || profileNicknameEditText.error == ""){
                    nickname = profileNicknameEditText.text.toString().trim()
                    viewModel.nicknameValidation(nickname)
                }
            }
        }
    }

    private fun checkNickname(){
        with(binding) {
            profileNicknameEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        profileNicknameTextLayout.error = "공백은 허용하지 않습니다."
                        profileSettingFinishButton.isEnabled = false

                    } else if (s.toString().length > 10){
                        profileNicknameTextLayout.error = "닉네임은 최대 10자까지 입력 가능합니다."
                        profileSettingFinishButton.isEnabled = false
                    }
                    else {
                        profileNicknameTextLayout.error = null
                        profileSettingFinishButton.isEnabled = true
                    }
                }
            })
        }
    }

    private fun observe() {
        viewModel.nicknameValidationLiveData.observe(this) {
            when (it) {
                "유효한 닉네임입니다." -> showConfirmDialog()
                else -> {
                    binding.profileNicknameEditText.error = it
                    binding.profileSettingFinishButton.isEnabled = false
                }
            }
        }
        viewModel.editProfileResponseLiveData.observe(this) {
            finishAffinity()
        }
    }

    private fun showConfirmDialog() {
        val dialog = Dialog(this)
        val dialogBinding = DialogDefaultBinding.inflate(LayoutInflater.from(this))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding) {
            titleText = "정말 입력된 정보로 변경하시겠습니까?\n확인을 누르고 앱을 재실행 해주세요."
            cancelText = "취소"
            confirmText = "확인"
            dialogCancelButton.setOnClickListener { dialog.dismiss() }
            dialogConfirmButton.setOnClickListener {
                imageUri?.let{ uri ->
                    viewModel.editProfile(this@ProfileSettingActivity, uri, nickname)
                }.run {
                    viewModel.editProfile(this@ProfileSettingActivity, null, nickname)
                }
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}