package kr.co.pureum.views.quest

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kr.co.domain.model.DataStamp
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataStampRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestAttendanceBinding

class QuestAttendanceFragment : BaseFragment<FragmentQuestAttendanceBinding>(R.layout.fragment_quest_attendance) {
    private val dataStampList : ArrayList<DataStamp> = arrayListOf()
    private val dataStampAdapter = DataStampRVAdapter(dataStampList)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initStamp()
        attendanceButton()
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

    private fun initStamp() {
        with(binding) {
            questAttendanceRv.layoutManager = GridLayoutManager(activity, 3)
            questAttendanceRv.adapter = dataStampAdapter

            dataStampList.apply {
                clear()
                for (i in 1..30)
                    dataStampList.apply {
                        add(DataStamp(R.drawable.stamp_gray))
                    }
                // 수정 어떻게..?
            }

        }

    }

    private fun attendanceButton() {
        binding.questAttendanceLayoutLl.setOnClickListener {
            when (binding.questAttendanceLayoutLl.isSelected) {
                true -> {
                    binding.questAttendanceLayoutLl.isSelected = false
                    binding.questAttendanceOkTextTv.text = "클릭해서 출석체크 하기"
                }
                false -> {
                    binding.questAttendanceLayoutLl.isSelected = true
                    binding.questAttendanceOkTextTv.text = "출석체크가 완료되었습니다"
                }
            }
        }
    }
}