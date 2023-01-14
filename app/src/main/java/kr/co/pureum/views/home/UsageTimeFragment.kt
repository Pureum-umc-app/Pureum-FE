package kr.co.pureum.views.home

import kr.co.pureum.R
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.view.View
import androidx.annotation.RequiresApi
import kr.co.domain.model.UsageTimeInfo
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentUsageTimeBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class UsageTimeFragment : BaseFragment<FragmentUsageTimeBinding>(R.layout.fragment_usage_time) {
    companion object {
        var instance: UsageTimeFragment? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        val date = LocalDate.of(requireArguments().getInt("year"), requireArguments().getInt("month"), requireArguments().getInt("day"))
        // TODO: 임시
        val usageTimeInfo = UsageTimeInfo(480, 345, date)
        if (date.isEqual(LocalDate.now())) {
            binding.homeToday.visibility = View.VISIBLE
        } else {
            binding.homeToday.visibility = View.INVISIBLE
        }
        binding.homeDate.text = date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))
        binding.homeUsageTimeIndicator.max = usageTimeInfo.goalTime
        binding.homeUsageTimeIndicator.progress = usageTimeInfo.usageTime

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

        binding.homeUsageTime.text = TextUtils.concat(hourSpan, hourTextSpan, "\n", minuteSpan, minuteTextSpan)
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}