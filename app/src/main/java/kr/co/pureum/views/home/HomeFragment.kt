package kr.co.pureum.views.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import kr.co.pureum.R
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
            homeUsageTimeViewPager.apply {
                adapter = UsageTimeAdapter(mainActivity).apply {
                    setListener(object : UsageTimeAdapter.Listener{
                        override fun onLoad() {
                            // TODO("Not yet implemented")
                        }
                    })
                }
                offscreenPageLimit = 3
                setPageTransformer { page, position ->
                    page.translationX = position * -(resources.displayMetrics.widthPixels
                            - resources.getDimensionPixelOffset(R.dimen.pageMargin)
                            - resources.getDimensionPixelOffset(R.dimen.pagerWidth))
                }
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        viewModel.prevUsageTimeLiveDate.value?.let {
                            usageTimeDto = it[position]
                            homeToday.visibility = when (LocalDate.of(it[position].year, it[position].month, it[position].day).isEqual(LocalDate.now())) {
                                true -> View.VISIBLE
                                else -> View.INVISIBLE
                            }
                        }

                    }
                })
            }
        }
    }

    private fun initListener() {
        with(binding) {
            homeMoreButton.setOnClickListener {

            }
        }
    }

    private fun observe() {
        viewModel.goalTimeLiveData.observe(viewLifecycleOwner) {

        }
        viewModel.prevUsageTimeLiveDate.observe(viewLifecycleOwner) {
            with(binding.homeUsageTimeViewPager) {
                (adapter as UsageTimeAdapter).setData(it)
                setCurrentItem(it.size, false)
            }
        }
        viewModel.prevRankLiveDate.observe(viewLifecycleOwner) {

        }
    }
}