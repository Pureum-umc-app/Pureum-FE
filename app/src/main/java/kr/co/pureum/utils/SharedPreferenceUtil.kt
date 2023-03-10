package kr.co.pureum.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context) {
    private val spfManager: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun spfClear() {
        spfManager.edit().clear().apply()
    }

    fun getAlarmTime(): Long = spfManager.getLong("alarm_time", 0)
    fun setAlarmTime(time: Long) {
        spfManager.edit().putLong("alarm_time", time).apply()
    }

    // userToken 메서드
    fun checkUserToken(): Boolean = spfManager.contains("user_token")
    fun getUserToken(): String = spfManager.getString("user_token", "").toString()
    fun setUserToken(token: String) {
        spfManager.edit().putString("user_token", "Bearer $token").apply()
    }

    // userId 메서드
    fun checkUserId() : Boolean = spfManager.contains("user_id")
    fun getUserId(): Long = spfManager.getLong("user_id", 0L)
    fun setUserId(userId: Long) {
        spfManager.edit().putLong("user_id", userId).apply()
    }

    // 목표 시간 설정 메서드
    fun checkPurposeTime(): Boolean = spfManager.contains("purpose_time")
    fun getPurposeTime(): Int = spfManager.getInt("purpose_time", 0)
    fun setPurposeTime(purposeTime: Int) {
        spfManager.edit().putInt("purpose_time", purposeTime).apply()
    }
    fun clearPurposeTime() {
        spfManager.edit().remove("purpose_time").apply()
    }

    // 출석체크 확인
    fun getAttendance(): Long = spfManager.getLong("attendance", 0)
    fun setAttendance(time: Long) {
        spfManager.edit().putLong("attendance", time).apply()
    }
}