package kr.co.pureum.views.quest

import android.os.Bundle
import android.view.View
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestLatestSentenceBinding

class QuestLatestSentenceFragment : BaseFragment<FragmentQuestLatestSentenceBinding>(R.layout.fragment_quest_latest_sentence) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.text1Tv.text = "최신순 화면"
    }
}