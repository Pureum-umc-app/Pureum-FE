package kr.co.pureum.views.signup

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBoardFourBinding

class OnBoardFourFragment : BaseFragment<FragmentOnBoardFourBinding>(R.layout.fragment_on_board_four) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
//        binding.profileTextView.text = "프로필 화면입니다."
//        Log.e("ScreenBuild", "ProfileFragment")
    }

    private fun initListener() {
        with(binding) {

            kakaoLoginIb.setOnClickListener {
                bottomSheetOpen()
            }
        }
    }

    private fun bottomSheetOpen() {

        var flag : Int = 0
        var service : Int = 0
        var info : Int = 0

        // bottomSheetDialog 객체 생성
        val bottomSheetDialog = activity?.let {
            BottomSheetDialog(
                it, R.style.BottomSheetDialogTheme
            )
        }



        // layout_bottom_sheet를 뷰 객체로 생성
        val bottomSheetView = layoutInflater.inflate(R.layout.sign_up_bottom_sheet, null)

        bottomSheetView.setBackgroundColor(Color.parseColor("#ffffff"))
        bottomSheetView.findViewById<View>(R.id.signup_agree_next_bt).setOnClickListener {

            if(flag==1 || (service==1)&&(info==1)){
                bottomSheetDialog?.dismiss()
                val intent = Intent(activity?.applicationContext, SignUpProfileActivity::class.java)
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)

//                finish()
//                overridePendingTransition(R.anim.not_move_activity,R.anim.rightout_activity)
            }


        }

        // 약관 자세히 보기
        bottomSheetView.findViewById<View>(R.id.service_agree_tv).setOnClickListener {
            val intent = Intent(activity?.applicationContext , SignUpClauseActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)
        }
        bottomSheetView.findViewById<View>(R.id.my_info_agree_tv).setOnClickListener {
            val intent = Intent(activity?.applicationContext, SignUpInfoActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)
        }


        val nextBt = bottomSheetView.findViewById<View>(R.id.signup_agree_next_bt) as Button
        val allButton = bottomSheetView.findViewById<View>(R.id.signup_all_agree_ib) as? ImageView
        val serviceBt = bottomSheetView.findViewById<View>(R.id.service_agree_ib) as ImageView
        val myInfoBt = bottomSheetView.findViewById<View>(R.id.my_info_agree_ib) as ImageView

        // 모두 동의를 눌렀을 때
        bottomSheetView.findViewById<View>(R.id.signup_all_agree_ib).setOnClickListener {

            if(flag==0){

                allButton?.setImageResource(R.drawable.ic_signup_fill)
                serviceBt.setImageResource(R.drawable.ic_signup_fill)
                myInfoBt.setImageResource(R.drawable.ic_signup_fill)
                flag = 1
                service = 1
                info = 1

                nextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(133,181,255))
                nextBt.setTextColor(Color.parseColor("#FFFFFF"))

            } else{

                allButton?.setImageResource(R.drawable.ic_signup_no_fill)
                serviceBt.setImageResource(R.drawable.ic_signup_no_fill)
                myInfoBt.setImageResource(R.drawable.ic_signup_no_fill)

                flag = 0
                service = 0
                info = 0

                nextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(216,236,255))
                nextBt.setTextColor(Color.parseColor("#6E6D73"))
            }
        }

        // 서비스만 동의
        bottomSheetView.findViewById<View>(R.id.service_agree_ib).setOnClickListener {
            if(service==0){
                serviceBt.setImageResource(R.drawable.ic_signup_fill)
                service = 1

                if(flag==1 || (service==1 && info==1)){
                    allButton?.setImageResource(R.drawable.ic_signup_fill)
                    nextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(133,181,255))
                    flag = 1
                }
            }
            else{
                serviceBt.setImageResource(R.drawable.ic_signup_no_fill)
                service = 0

                allButton?.setImageResource(R.drawable.ic_signup_no_fill)
                flag = 0
                nextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(216,236,255))
                nextBt.setTextColor(Color.parseColor("#6E6D73"))

            }
        }

        // 개인정보만 동의
        bottomSheetView.findViewById<View>(R.id.my_info_agree_ib).setOnClickListener {
            if(info==0){
                myInfoBt.setImageResource(R.drawable.ic_signup_fill)
                info = 1

                if(flag==1 || (service==1 && info==1)){
                    allButton?.setImageResource(R.drawable.ic_signup_fill)
                    nextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(133,181,255))
                    flag = 1
                }
            }
            else{
                myInfoBt.setImageResource(R.drawable.ic_signup_no_fill)
                info = 0

                allButton?.setImageResource(R.drawable.ic_signup_no_fill)
                flag = 0
                nextBt.backgroundTintList = ColorStateList.valueOf(Color.rgb(216,236,255))
                nextBt.setTextColor(Color.parseColor("#6E6D73"))
            }
        }




        // bottomSheetDialog 뷰 생성
        bottomSheetDialog?.setContentView(bottomSheetView)

        // bottomSheetDialog 호출
        bottomSheetDialog?.show()


    }
}