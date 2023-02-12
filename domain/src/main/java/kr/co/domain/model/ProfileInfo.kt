package kr.co.domain.model

import com.google.gson.annotations.SerializedName

data class ProfileInfo(
    val grade: Int,
    val nickname: String,

    @SerializedName("profile_photo_url")
    val profileUrl: String
)