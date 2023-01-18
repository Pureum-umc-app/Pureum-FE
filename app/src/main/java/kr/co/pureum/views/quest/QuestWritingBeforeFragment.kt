package kr.co.pureum.views.quest

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding

class QuestWritingBeforeFragment : BaseFragment<FragmentQuestWritingBeforeBinding>(R.layout.fragment_quest_writing_before) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        binding.questTodayKeywordOneTv.text = "구현"
        binding.questTodayKeywordTwoTv.text = "바보"
        binding.questTodayKeywordThreeTv.text = "호구"
    }

    private fun initListener() {
        with(binding) {
            questTodaySentenceOneCv.setOnClickListener {
                val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment()
                findNavController().navigate(action)
            }
        }
    }

}