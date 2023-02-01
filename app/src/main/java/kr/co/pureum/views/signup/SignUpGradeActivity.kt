package kr.co.pureum.views.signup

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivitySignUpGradeBinding


class SignUpGradeActivity : BaseActivity<ActivitySignUpGradeBinding>(kr.co.pureum.R.layout.activity_sign_up_grade) {

    override fun initView() {
        gradeDropdown()
    }

    fun gradeDropdown(){
        with(binding){

            var grade = ""

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        // 선택안함
                        0 -> {
                            grade = ""
                            buttonOnOff(0)
                        }
                        // 초등학생
                        1 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        // 중학교 1학년
                        2 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        // 중학교 2학년
                        3 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        // 중학교 3학년
                        4 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        // 고등학교 1학년
                        5 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        // 고등학교 2학년
                        6 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        // 고등학교 3학년, N수생
                        7 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        // 대학생
                        8 -> {
                            grade = spinner.selectedItem.toString()
                            buttonOnOff(1)
                        }
                        //일치하는게 없는 경우
                        else -> {
                            grade = ""
                            buttonOnOff(0)
                        }
                    }
                }
            }
        }

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

}