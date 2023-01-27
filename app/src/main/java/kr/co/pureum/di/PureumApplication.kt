package kr.co.pureum.di

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp
import kr.co.pureum.BuildConfig
import kr.co.pureum.utils.SharedPreferenceUtil

@HiltAndroidApp
class PureumApplication: Application() {
    companion object {
        private lateinit var application: PureumApplication
        lateinit var spfManager: SharedPreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들
        spfManager = SharedPreferenceUtil(applicationContext)
        application = this

        // Kakao SDK 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)

        // keyHash 출력
        val keyHash = Utility.getKeyHash(this)
        Log.e("keyHash", keyHash)
    }
}