package kr.co.pureum.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator

object CustomBinder {
    @JvmStatic
    @BindingAdapter("app:indicatorColor")
    fun setIndicatorColor(progress: CircularProgressIndicator, color: Int) {
        progress.setIndicatorColor(color)
    }
}