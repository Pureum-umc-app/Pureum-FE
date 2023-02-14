package kr.co.pureum.views.quest

import android.app.Activity
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.domain.model.DataSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataSentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestBinding
import kr.co.pureum.di.PureumApplication

@AndroidEntryPoint
class QuestFragment : BaseFragment<FragmentQuestBinding>(R.layout.fragment_quest) {
    private val viewModel by viewModels<QuestViewModel>()
    private val questNavArgs by navArgs<QuestFragmentArgs>()

    private var _init = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (_init) checkNavArgs()
        initToolbar()
        initView()
        initApplySentenceView()
        initListener()
        observe()
        activity?.let { updateStatusBarColor(it, "#D8ECFF") }
        if (requireActivity().intent.hasExtra("badge")) {
            activity?.let { updateStatusBarColor(it, "#F8F8F8") }
            val action = QuestFragmentDirections.actionQuestFragmentToQuestBadgeFragment()
            findNavController().navigate(action)
            requireActivity().intent.removeExtra("badge")
        }
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

    private fun checkNavArgs() {
        when (questNavArgs.toBadge) {
            true -> findNavController().navigate(QuestFragmentDirections.actionQuestFragmentToQuestBadgeFragment())
            else -> {}
        }
        _init = false
    }

    private fun initToolbar() {
        with(binding.mainToolbar) {
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        Log.e("ScreenBuild", "QuestFragment")
        binding.isLoading = true
        viewModel.getTodayKeyword()
        binding.nickname = "태우"
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
            questBadgeButton.setOnClickListener {
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
        binding.questKeywordViewRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.questKeywordViewRv.adapter = DataSentenceRVAdapter()
    }

    private fun observe() {
        viewModel.todayKeywordListLiveData.observe(viewLifecycleOwner) {
            (binding.questKeywordViewRv.adapter as DataSentenceRVAdapter).setData(it)
            binding.isLoading = false
            Log.d(TAG, it.toString())
            Log.e(TAG, PureumApplication.spfManager.getUserId().toString())

        }
    }
}