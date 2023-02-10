package kr.co.pureum.views.signup

import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import com.google.android.material.R.*
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpGradeBinding

class SignUpGradeActivity : BaseActivity<ActivitySignUpGradeBinding>(R.layout.activity_sign_up_grade) {

    override fun initView() {
        with(binding) {
            signupGradeNextButton.isEnabled = false
        }
        initDropDown()
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
                    signupGradeNextButton.isEnabled = true
                    dismiss()
                }
            }
            gradeDropdown.setOnClickListener { listFoodPopupWindow.show() }
        }
    }
}