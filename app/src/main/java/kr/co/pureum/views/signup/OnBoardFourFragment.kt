package kr.co.pureum.views.signup

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.BottomSheetClauseBinding
import kr.co.pureum.databinding.BottomSheetSetGoalTimeBinding
import kr.co.pureum.databinding.FragmentOnBoardFourBinding
import kr.co.pureum.di.PureumApplication
import kr.co.pureum.views.MainActivity

@AndroidEntryPoint
class OnBoardFourFragment : BaseFragment<FragmentOnBoardFourBinding>(R.layout.fragment_on_board_four) {
    private val viewModel by viewModels<OnBoardViewModel>()
    lateinit var kakaoToken : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {}

    private fun initListener() {
        with(binding) {
            kakaoLoginIb.setOnClickListener {
                kakaoLogin()
            }
        }
    }

    private fun observe() {
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) {
            when (it.jwt) {
                "error" -> openClauseBottomSheet()
                else -> {
                    PureumApplication.spfManager.setUserToken(it.jwt)
                    PureumApplication.spfManager.setUserId(it.userId)
                    val intent = Intent(requireContext(), MainActivity::class.java).apply {
                        putExtra("screen", 1)
                    }
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }
    }

    private lateinit var userName: String
    private lateinit var userProfile : String
    private var userId: Long = 0
    private var userGender: Boolean = true

    private fun startLogin() {
        UserApiClient.instance.me { user, _ ->
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
            kakaoToken = token.accessToken
            Log.i(TAG, "카카오계정으로 로그인 성공 $kakaoToken")
            startLogin()
            viewModel.login(kakaoToken)
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

    private fun openClauseBottomSheet() {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val dialogBinding =
            BottomSheetClauseBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(dialogBinding.root)
        with(dialogBinding) {
            clauseNextButton.isEnabled = false

            allAgreeCheckBox.setOnClickListener {
                serviceAgreeCheckBox.isChecked = allAgreeCheckBox.isChecked
                personalInfoAgreeCheckBox.isChecked = allAgreeCheckBox.isChecked
                clauseNextButton.isEnabled = allAgreeCheckBox.isChecked
            }

            serviceAgreeTextView.setOnClickListener {
                val intent = Intent(requireContext(), SignUpClauseActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)
            }
            serviceAgreeCheckBox.setOnClickListener {
                allAgreeCheckBox.isChecked = serviceAgreeCheckBox.isChecked && personalInfoAgreeCheckBox.isChecked
                clauseNextButton.isEnabled = allAgreeCheckBox.isChecked
            }

            personalInfoAgreeTextView.setOnClickListener {
                val intent = Intent(requireContext(), SignUpClauseActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)
            }
            personalInfoAgreeCheckBox.setOnClickListener {
                allAgreeCheckBox.isChecked = serviceAgreeCheckBox.isChecked && personalInfoAgreeCheckBox.isChecked
                clauseNextButton.isEnabled = allAgreeCheckBox.isChecked
            }

            clauseNextButton.setOnClickListener {
                startActivity(Intent(requireContext(), SignUpProfileActivity::class.java).apply {
                    putExtra("kakaoToken", kakaoToken)
                })
                requireActivity().finish()
                requireActivity().overridePendingTransition(R.anim.rightin_activity,R.anim.not_move_activity)
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}