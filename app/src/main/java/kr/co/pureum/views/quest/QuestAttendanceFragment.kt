package kr.co.pureum.views.quest

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.domain.model.DataStamp
import kr.co.pureum.R
import kr.co.pureum.adapter.home.UsageTimeAdapter
import kr.co.pureum.adapter.quest.AttendanceSheetAdapter
import kr.co.pureum.adapter.quest.DataStampRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestAttendanceBinding
import kr.co.pureum.di.PureumApplication
import java.util.*

@AndroidEntryPoint
class QuestAttendanceFragment : BaseFragment<FragmentQuestAttendanceBinding>(R.layout.fragment_quest_attendance) {
    private val viewModel by viewModels<AttendanceViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
        observe()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = androidx.core.content.ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initView() {
        viewModel.getStampList()
        with(binding) {
            isChecked = isToday(PureumApplication.spfManager.getAttendance())
            questAttendanceViewPager.apply {
                adapter = AttendanceSheetAdapter()
                offscreenPageLimit = 3
                setPageTransformer { page, position ->
                    page.translationX = position * -(resources.displayMetrics.widthPixels - resources.getDimensionPixelOffset(R.dimen.pageMargin) - resources.getDimensionPixelOffset(R.dimen.pagerWidth))
                }
            }
        }
    }

    private fun initListener() {
        with(binding) {
            questAttendanceButton.setOnClickListener {
                questAttendanceButton.isClickable = false
                viewModel.attendanceCheck()
            }
        }
    }

    private fun observe() {
        viewModel.stampsLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                accumulatedCnt = it.accumulatedCnt
                if (accumulatedCnt == 30) achieveBadge()
                val stampSheetList = MutableList(it.accumulatedCnt / 12) { 12 }
                stampSheetList.add(it.currentCnt)
                with(questAttendanceViewPager) {
                    (adapter as AttendanceSheetAdapter).setData(stampSheetList)
                    setCurrentItem(stampSheetList.size, false)
                }
                (questAttendanceViewPager.adapter as AttendanceSheetAdapter).setData(stampSheetList)
            }
        }
        viewModel.attendanceLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                viewModel.getStampList()
                isChecked = true
            }
            PureumApplication.spfManager.setAttendance(System.currentTimeMillis())
        }
        badgeViewModel.badgeResultLiveData.observe(viewLifecycleOwner) {
            badgeViewModel.showBadgeAchieveDialog(requireContext(), requireActivity(),1)
        }
    }

    private val badgeViewModel by viewModels<QuestBadgeViewModel>()
    private fun achieveBadge() {
        badgeViewModel.saveBadge(1)
    }

    private fun isToday(timeInMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_YEAR)
        calendar.timeInMillis = timeInMillis
        val otherDay = calendar.get(Calendar.DAY_OF_YEAR)
        return today == otherDay
    }
}