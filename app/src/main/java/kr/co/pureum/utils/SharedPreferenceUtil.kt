package kr.co.pureum.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context) {
    private val spfManager: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun spfClear() {
        spfManager.edit().clear().apply()
    }

    // userToken 메서드
    fun checkUserToken() : Boolean = spfManager.contains("user_token")
    fun getUserToken(): String = spfManager.getString("user_token", "").toString()
    fun setUserToken(token: String) {
        spfManager.edit().putString("user_token", token).apply()
    }

    // 목표 시간 설정 메서드
    fun checkPurposeTime() : Boolean = spfManager.contains("purpose_time")
    fun getPurposeTime(): Int = spfManager.getInt("purpose_time", 0)
    fun setPurposeTime(purposeTime: Int) {
        spfManager.edit().putInt("purpose_time", purposeTime).apply()
    }
    fun clearPurposeTime() {
        spfManager.edit().remove("purpose_time").apply()
    }
}