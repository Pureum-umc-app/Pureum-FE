package kr.co.pureum.views.signup

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.widget.ListPopupWindow
import com.google.android.material.R.*
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpGradeBinding
import kr.co.pureum.views.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class SignUpGradeActivity : BaseActivity<ActivitySignUpGradeBinding>(R.layout.activity_sign_up_grade) {
    private val viewModel by viewModels<OnBoardViewModel>()
    private var grade = -1

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
            signupGradeNextButton.setOnClickListener {
                val imagePath = intent.getStringExtra("imagePath").toString()
                val nickname = intent.getStringExtra("nickname").toString()
                val kakaoToken = intent.getStringExtra("kakaoToken").toString()
                viewModel.signup(imagePath, grade, nickname, kakaoToken)
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
            Log.e(TAG, it)
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("screen", 1)
            })
            finish()
        }
    }
}