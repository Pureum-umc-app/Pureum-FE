package kr.co.pureum.views.signup

import android.content.ContentValues.TAG
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import com.google.android.material.R.*
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpGradeBinding

class SignUpGradeActivity : BaseActivity<ActivitySignUpGradeBinding>(R.layout.activity_sign_up_grade) {

    override fun initView() {
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
                    buttonOnOff(1)
                    dismiss()
                }
            }

            gradeDropdown.setOnClickListener { listFoodPopupWindow.show() }
        }
    }

    private fun buttonOnOff(flag: Int){
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

}