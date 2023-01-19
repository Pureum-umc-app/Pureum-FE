package kr.co.pureum.views.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.pureum.R
import kr.co.pureum.adapter.home.RankingAdapter
import kr.co.pureum.adapter.home.UsageTimeAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.BottomSheetSetGoalTimeBinding
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
            supportActionBar!!.setDisplayUseLogoEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    // Add menu items here
                    menuInflater.inflate(R.menu.menu_toolbar, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    // Handle the menu selection
                    return when (menuItem.itemId) {
                        R.id.toolbar_calender -> {
                            // TODO: 월간 목표 달성 여부 확인 페이지로 이동
                            true
                        }
                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
                            var date = LocalDate.of(it[position].year, it[position].month, it[position].day)
                            isToday = date.isEqual(LocalDate.now())
                            usageTimeDto = it[position]
                            goalTime = it[position].goalTime
                            if (date.isEqual(LocalDate.now())) {
                                date = date.minusDays(1)
                                goalTime = viewModel.goalTimeLiveData.value
                            }
                            viewModel.changeDate(date.year, date.monthValue, date.dayOfMonth)
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
            homeGoalTimeLayout.setOnClickListener {
                val dialog = BottomSheetDialog(mainActivity)
                val dialogBinding = BottomSheetSetGoalTimeBinding.inflate(LayoutInflater.from(requireContext()))
                dialog.setContentView(dialogBinding.root)
                with(dialogBinding) {
                    var time = 0
                    homeDialogExitButton.setOnClickListener {
                        dialog.dismiss()
                    }
                    homeDialogDecreaseButton.setOnClickListener {
                        if (time in 1..24) time -= 1
                        hour = time
                    }
                    homeDialogIncreaseButton.setOnClickListener {
                        if (time in 0..23) time += 1
                        hour = time
                    }
                    homeDialogNextButton.setOnClickListener {
                        dialog.dismiss()
                    }
                }
                dialog.show()
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