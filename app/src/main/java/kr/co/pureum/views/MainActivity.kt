package kr.co.pureum.views

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {
        updateKakaoLoginUi()
        initListener()
    }

    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
        updateKakaoLoginUi()
    }

    private fun kakaoLogin() {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
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
            updateKakaoLoginUi()
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
        updateKakaoLoginUi()
    }

    // 사용자가 카카오톡 계정으로 로그인이 되어 있는 지 확인하고,
    //   로그인되어 있지 않다면 로그인 버튼을 출력하고,
    //   로그인이 되어 있다면 사용자 프로필과 닉네임, 로그아웃 버튼을 출력하는 메서드
    private fun updateKakaoLoginUi() {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (user == null) {
                binding.kakaoLoginBtn.visibility = View.VISIBLE
                binding.kakaoDeleteBtn.visibility = View.GONE
                binding.kakaoLogoutBtn.visibility = View.GONE
                binding.kakaoName.visibility = View.GONE
                binding.kakaoProfile.visibility = View.GONE
            }
            else {
                binding.kakaoLoginBtn.visibility = View.GONE
                binding.kakaoLogoutBtn.visibility = View.VISIBLE
                binding.kakaoDeleteBtn.visibility = View.VISIBLE
                Log.i(TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                binding.kakaoName.text = user.kakaoAccount?.profile?.nickname
                Glide.with(binding.kakaoProfile).load(user.kakaoAccount?.profile?.thumbnailImageUrl).circleCrop().into(binding.kakaoProfile)
                binding.kakaoName.visibility = View.VISIBLE
                binding.kakaoProfile.visibility = View.VISIBLE
            }
        }
    }

    private fun initListener() {
        binding.kakaoLoginBtn.setOnClickListener {
            kakaoLogin()
        }
        binding.kakaoLogoutBtn.setOnClickListener {
            kakaoLogout()
        }
        binding.kakaoDeleteBtn.setOnClickListener {
            kakaoDelete()
        }
    }
}