package kr.co.pureum.views.quest

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.domain.model.DataSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataSentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestBinding


class QuestFragment : BaseFragment<FragmentQuestBinding>(R.layout.fragment_quest) {
    //private val viewModel by viewModels<QuestViewModel>()
    private val dataSentenceList : ArrayList<DataSentence> = arrayListOf()
    private val dataSentenceAdapter = DataSentenceRVAdapter(dataSentenceList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initApplySentenceView()
        initListener()
        activity?.let { updateStatusBarColor(it, "#D8ECFF") }
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "onResume")
        activity?.let { updateStatusBarColor(it, "#D8ECFF") }
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "onPause")
        activity?.let { updateStatusBarColor(it, "#F8F8F8") }
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        Log.e("ScreenBuild", "QuestFragment")
    }

    // 상태바 color 지정 함수
    private fun updateStatusBarColor(context: Activity, color: String?) {
        val window = context.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(color)
    }

    private fun initListener() {
        with(binding) {
            questActionLl.setOnClickListener {
                val action = QuestFragmentDirections.actionQuestFragmentToQuestClickFragment()
                findNavController().navigate(action)
            }
            questGoBadgeBt.setOnClickListener {
                val action = QuestFragmentDirections.actionQuestFragmentToQuestBadgeFragment()
                findNavController().navigate(action)
            }
            questAttendanceGoLl.setOnClickListener {
                val action = QuestFragmentDirections.actionQuestFragmentToQuestAttendanceFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun initApplySentenceView() {

        val managerSentence = LinearLayoutManager(activity)
        managerSentence.reverseLayout = true
        managerSentence.stackFromEnd = true
        binding.questKeywordViewRv.layoutManager = managerSentence
        binding.questKeywordViewRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.questKeywordViewRv.adapter = dataSentenceAdapter

        dataSentenceList.clear()
        dataSentenceList.apply {
            add(DataSentence("구현"))
            add(DataSentence("바보"))
            add(DataSentence( "호구"))
        }
    }
    /*
    private fun observe() {
        viewModel.todayKeywordLiveData.observe(viewLifecycleOwner) {
            (binding.questKeywordViewRv.adapter as DataSentenceRVAdapter)
        }
    }
    */

}