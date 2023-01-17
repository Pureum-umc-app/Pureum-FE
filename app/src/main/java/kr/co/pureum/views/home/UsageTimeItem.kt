package kr.co.pureum.views.home

import kr.co.pureum.R
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.view.View
import kr.co.domain.model.UsageTimeInfo
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.ItemHomeUsageTimeBinding


class UsageTimeItem : BaseFragment<ItemHomeUsageTimeBinding>(R.layout.item_home_usage_time) {
    companion object {
        var instance: UsageTimeItem? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        // TODO: 임시
        val usageTimeInfo = UsageTimeInfo(480, 345, requireArguments().getInt("year"), requireArguments().getInt("month"), requireArguments().getInt("day"))

        with(binding) {
            if (requireArguments().getInt("year") == 1) homeToday.visibility = View.VISIBLE
            else homeToday.visibility = View.INVISIBLE
            homeDate.text = "%d년 %d월 %d일".format(requireArguments().getInt("year"), requireArguments().getInt("month"), requireArguments().getInt("day"))
            homeUsageTimeIndicator.max = usageTimeInfo.goalTime
            homeUsageTimeIndicator.progress = usageTimeInfo.usageTime

            val hour = (usageTimeInfo.usageTime / 60).toString()
            val minute = (usageTimeInfo.usageTime % 60).toString()

            val hourSpan = SpannableString(hour).apply {
                setSpan(AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen.text_size_40dp)),
                    0, hour.length, SPAN_INCLUSIVE_INCLUSIVE)
            }
            val hourTextSpan = SpannableString("시간").apply {
                setSpan(AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen.text_size_22dp)),
                    0, "시간".length, SPAN_INCLUSIVE_INCLUSIVE)
            }
            val minuteSpan = SpannableString(minute).apply {
                setSpan(AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen.text_size_40dp)),
                    0, minute.length, SPAN_INCLUSIVE_INCLUSIVE)
            }
            val minuteTextSpan = SpannableString("분").apply {
                setSpan(AbsoluteSizeSpan(resources.getDimensionPixelSize(R.dimen.text_size_22dp)),
                    0, "분".length, SPAN_INCLUSIVE_INCLUSIVE)
            }

            homeUsageTime.text = TextUtils.concat(hourSpan, hourTextSpan, "\n", minuteSpan, minuteTextSpan)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}