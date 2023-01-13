package kr.co.pureum.views.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.domain.model.UsageTimeInfo
import kr.co.pureum.R
import kr.co.pureum.adapter.home.UsageTimeAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentHomeBinding
import kr.co.pureum.views.MainActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var mainActivity: MainActivity
    private lateinit var today: LocalDateTime

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        mainActivity = context as MainActivity
        with(mainActivity) {
            supportActionBar?.setDisplayUseLogoEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        today = LocalDateTime.now()

        binding.homeViewPager.apply {
            adapter = UsageTimeAdapter().apply {
                setListener(object : UsageTimeAdapter.Listener{
                    override fun onClick(combId: Long) {
                    }

                    override fun onDelete(combId: Long) {
                    }

                    override fun onPost(combId: Long) {
                    }
                })
            }
        }

        val usageTimeList : List<UsageTimeInfo> = listOf(UsageTimeInfo(10, 7, LocalDateTime.now()), UsageTimeInfo(10, 7, LocalDateTime.now()), UsageTimeInfo(10, 7, LocalDateTime.now()))
        (binding.homeViewPager.adapter as UsageTimeAdapter).setData(usageTimeList)
    }
}