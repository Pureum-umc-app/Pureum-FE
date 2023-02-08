package kr.co.pureum.views.signup

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBoardFourBinding
import kr.co.pureum.di.PureumApplication
import kr.co.pureum.views.MainActivity

@AndroidEntryPoint
class OnBoardFourFragment : BaseFragment<FragmentOnBoardFourBinding>(R.layout.fragment_on_board_four) {
    private val viewModel by viewModels<OnBoardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        if (PureumApplication.spfManager.checkUserToken()) {
            val intent = Intent(requireContext(), MainActivity::class.java).apply {
                putExtra("screen", 1)
            }
            startActivity(intent)
            requireActivity().finish()
        } else {
            binding.kakaoLoginIb.visibility = View.VISIBLE
        }
    }

    private fun initListener() {
        with(binding) {
            kakaoLoginIb.setOnClickListener {
                kakaoLogin()
//                bottomSheetOpen()
            }
            kakaoTempButton.setOnClickListener {
                kakaoLogout()
            }
        }
    }

    private lateinit var userName: String
    private lateinit var userProfile : String
    private var userId: Long = 0
    private var userGender: Boolean = true

    private fun startLogin() {
        UserApiClient.instance.me { user, error ->
            if (user != null) {
                userName = user.kakaoAccount?.profile?.nickname.toString()
                userProfile = user.kakaoAccount?.profile?.thumbnailImageUrl.toString()
                userGender = user.kakaoAccount?.gender.toString() == "MALE"
                user.id?.let {
                    userId = it
                }
                Log.e(TAG, user.toString())
            }
        }
    }

    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            startLogin()
        }
    }

    private fun kakaoLogin() {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    startLogin()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    private fun kakaoLogout() {
        // 로그아웃
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            }
            else {
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }

    private fun kakaoDelete() {
        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(TAG, "연결 끊기 실패", error)
            }
            else {
                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
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