package kr.co.pureum.views.signup

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.net.toUri
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.R.*
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpGradeBinding
import kr.co.pureum.di.PureumApplication
import kr.co.pureum.utils.UriParser
import kr.co.pureum.views.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class SignUpGradeActivity : BaseActivity<ActivitySignUpGradeBinding>(R.layout.activity_sign_up_grade) {
    private val viewModel by viewModels<OnBoardViewModel>()
    private var grade = -1

    companion object{
        private val REQUIRED_EXTERNAL_STORAGE_PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
        isGranted.forEach {
            if (it.value){
                Log.e(TAG, "외부 저장소 읽기")
            } else {
//                showToast("권한 동의 후 다시 한번 클릭해주세요.")
            }
        }
    }

    override fun initView() {
        with(binding) {
            signupGradeNextButton.isEnabled = false
        }
        initListener()
        initDropDown()
        observe()
    }

    private fun initListener() {
        with(binding) {
            requestPermissionLauncher.launch(REQUIRED_EXTERNAL_STORAGE_PERMISSIONS)
            signupGradeNextButton.setOnClickListener {
                val nickname = intent.getStringExtra("nickname").toString()
                val imageUri = intent.getStringExtra("imageUri")?.toUri()
                val kakaoToken = intent.getStringExtra("kakaoToken").toString()
//                UriParser().getPath(this@SignUpGradeActivity, imageUri)
                viewModel.signup(this@SignUpGradeActivity, imageUri, grade, nickname, kakaoToken)
            }
        }
    }

    private fun initDropDown() {
        with(binding) {
            val listFoodPopupWindow = ListPopupWindow(this@SignUpGradeActivity, null, attr.listPopupWindowStyle)
            listFoodPopupWindow.apply {
                anchorView = gradeDropdown
                val items = listOf("초등학생", "중학교 1학년", "중학교 2학년", "중학교 3학년", "고등학교 1학년", "고등학교 2학년", "고등학교 3학년, N수생", "대학생")
                val adapter = ArrayAdapter(this@SignUpGradeActivity, R.layout.item_dropdown, items)
                setAdapter(adapter)
                setOnItemClickListener { _, _, position, _ ->
                    gradeDropdown.setText(items[position])
                    grade = position
                    signupGradeNextButton.isEnabled = true
                    dismiss()
                }
            }
            gradeDropdown.setOnClickListener { listFoodPopupWindow.show() }
        }
    }

    private fun observe() {
        viewModel.signupResponseLiveData.observe(this) {
            viewModel.login(intent.getStringExtra("kakaoToken").toString())
        }
        viewModel.userInfoLiveData.observe(this) {
            when (it.jwt) {
                "error" -> {
                    Log.e(TAG, "로그인 실패")
                    finishAffinity()
                }
                else -> {
                    PureumApplication.spfManager.setUserToken(it.jwt)
                    PureumApplication.spfManager.setUserId(it.userId)
                    startActivity(Intent(this, MainActivity::class.java).apply {
                        putExtra("screen", 1)
                    })
                    finish()
                }
            }
        }
    }
}