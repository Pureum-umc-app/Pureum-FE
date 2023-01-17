package kr.co.pureum.views.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import kr.co.pureum.R
import kr.co.pureum.adapter.home.RankingAdapter
import kr.co.pureum.adapter.home.UsageTimeAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentHomeBinding
import kr.co.pureum.views.MainActivity
import java.time.LocalDate

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var mainActivity: MainActivity
    private val viewModel by viewModels<HomeViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
        observe()
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
        viewModel.getHomeInfo()
        with(binding) {
            isToday = true
            homeUsageTimeViewPager.apply {
                adapter = UsageTimeAdapter(mainActivity)
                offscreenPageLimit = 3
                setPageTransformer { page, position ->
                    page.translationX = position * -(resources.displayMetrics.widthPixels - resources.getDimensionPixelOffset(R.dimen.pageMargin) - resources.getDimensionPixelOffset(R.dimen.pagerWidth))
                }
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        viewModel.prevUsageTimeLiveData.value?.let {
                            viewModel.changeDate(it[position].year, it[position].month, it[position].day)
                            usageTimeDto = it[position]
                            isToday = LocalDate.of(it[position].year, it[position].month, it[position].day).isEqual(LocalDate.now())
                        }

                    }
                })
            }
            homeRankRecyclerView.apply {
                adapter = RankingAdapter(mainActivity)
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }
    }

    private fun initListener() {
        with(binding) {
            homeMoreButton.setOnClickListener {
                // TODO: 랭킹 전체 화면으로 이동
            }
            homeGoalTime.setOnClickListener {
                // TODO: 목표 시간 설정 다이얼로그 표시
            }
        }
    }

    private fun observe() {
        viewModel.goalTimeLiveData.observe(viewLifecycleOwner) {

        }
        viewModel.prevUsageTimeLiveData.observe(viewLifecycleOwner) {
            with(binding.homeUsageTimeViewPager) {
                (adapter as UsageTimeAdapter).setData(it)
                setCurrentItem(it.size, false)
            }
        }
        viewModel.prevRankLiveData.observe(viewLifecycleOwner) {
            (binding.homeRankRecyclerView.adapter as RankingAdapter).setData(it)
        }
    }
}