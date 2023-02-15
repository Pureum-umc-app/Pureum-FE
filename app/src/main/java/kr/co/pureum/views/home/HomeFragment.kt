package kr.co.pureum.views.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.home.RankingAdapter
import kr.co.pureum.adapter.home.UsageTimeAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.BottomSheetCalendarBinding
import kr.co.pureum.databinding.BottomSheetSetGoalTimeBinding
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.databinding.FragmentHomeBinding
import kr.co.pureum.di.PureumApplication.Companion.spfManager
import kr.co.pureum.views.MainActivity
import java.time.LocalDate

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private var _isLoading: Boolean = true

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
        observe()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_toolbar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                when (menuItem.itemId) {
                    R.id.toolbar_calender -> {
                        showCalendarDialog(requireContext())
                        true
                    }
                    else -> false
                }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        with((requireActivity() as MainActivity)) {
            viewModel.getHomeInfo(totalUsageTime, screenCount)
        }
        with(binding) {
            isLoading = true
            _isLoading = true
            isToday = true
            homeUsageTimeViewPager.apply {
                adapter = UsageTimeAdapter()
                offscreenPageLimit = 3
                setPageTransformer { page, position ->
                    page.translationX = position * -(resources.displayMetrics.widthPixels - resources.getDimensionPixelOffset(R.dimen.pageMargin) - resources.getDimensionPixelOffset(R.dimen.pagerWidth))
                }
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        viewModel.homeInfoListLiveData.value?.let {
                            val dateValue = listOf(it[position].date.year, it[position].date.month, it[position].date.day)
                            binding.dateValue = dateValue
                            val date = LocalDate.of(dateValue[0], dateValue[1], dateValue[2])
                            isToday = when(date.isEqual(today)) {
                                true -> {
                                    homeGoalTimeLayout.isClickable = true
                                    homeGoalTime.text = if (spfManager.checkPurposeTime()) "${spfManager.getPurposeTime() / 60}시간"
                                    else "설정하기"
                                    screenCount = it[position].count
                                    true
                                }
                                else -> {
                                    homeGoalTimeLayout.isClickable = false
                                    viewModel.changeDate(date)
                                    homeGoalTime.text = if(it[position].purposeTime.minutes >= it[position].useTime.minutes) "달성 성공!" else "시간 초과"
                                    screenCount = it[position].count
                                    false
                                }
                            }
                        }
                    }
                })
            }
            homeRankRecyclerView.apply {
                adapter = RankingAdapter(requireContext())
                layoutManager = LinearLayoutManager(requireActivity())
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun initListener() {
        with(binding) {
            homeMoreButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToRankingFragment()
                findNavController().navigate(action)
            }
            homeGoalTimeLayout.setOnClickListener {
                showGoalTimeDialog( when(spfManager.checkPurposeTime()) {
                    true -> spfManager.getPurposeTime() / 60
                    else -> 1
                })
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun observe() {
        viewModel.homeInfoListLiveData.observe(viewLifecycleOwner) {
            if (_isLoading) {
                with(binding.homeUsageTimeViewPager) {
                    (adapter as UsageTimeAdapter).setData(it)
                    // currentItem = adapter!!.itemCount - 1
                    setCurrentItem(it.size, false)
                }
                viewModel.changeDate(today.minusDays(1))
                binding.isLoading = false
                _isLoading = false
            }
        }
        viewModel.prevRankLiveData.observe(viewLifecycleOwner) {
            (binding.homeRankRecyclerView.adapter as RankingAdapter).setData(it)
        }
        viewModel.updatedGoalTimeLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                (homeUsageTimeViewPager.adapter as UsageTimeAdapter)
                    .updateData(viewModel.homeInfoListLiveData.value!!)
                homeGoalTime.text = "${it/60}시간"
                isLoading = false
            }
        }
    }

    companion object {
        fun showCalendarDialog(context: Context) {
            val dialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme).apply {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            val dialogBinding =
                BottomSheetCalendarBinding.inflate(LayoutInflater.from(context))
            dialog.setContentView(dialogBinding.root)
            with(dialogBinding) {
                homeCalendarExitButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }

    private fun showGoalTimeDialog(_time: Int) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        val dialogBinding =
            BottomSheetSetGoalTimeBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(dialogBinding.root)
        with(dialogBinding) {
            hour = _time
            homeDialogExitButton.setOnClickListener { dialog.dismiss() }

            var time = _time
            homeDialogDecreaseButton.setOnClickListener {
                if (time in 2..24) time -= 1
                hour = time
            }
            homeDialogIncreaseButton.setOnClickListener {
                if (time in 1..23) time += 1
                hour = time
            }

            homeDialogNextButton.setOnClickListener { showConfirmDialog(dialog, time*60) }
        }
        dialog.show()
    }

    private fun showConfirmDialog(parentDialog: BottomSheetDialog, time: Int) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogDefaultBinding.inflate(LayoutInflater.from(requireContext()))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding) {
            titleText = "목표시간은 한 번만 설정 가능합니다.\n설정하시겠습니까?"
            cancelText = "다시 설정"
            confirmText = "확인"
            dialogCancelButton.setOnClickListener { dialog.dismiss() }
            dialogConfirmButton.setOnClickListener {
                // TODO: 서버에 설정 결과 전송
                viewModel.updateGoalTime(time)
                binding.isLoading = true
                dialog.dismiss()
                parentDialog.dismiss()
            }
            dialog.show()
        }
    }
}