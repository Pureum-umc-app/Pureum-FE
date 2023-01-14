package kr.co.pureum.views.quest

import android.os.Bundle
import android.util.Log
import android.view.View
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestBadgeBinding
import kr.co.pureum.views.MainActivity

class QuestBadgeFragment : BaseFragment<FragmentQuestBadgeBinding>(R.layout.fragment_quest_badge) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.badgeTextView.text = "배지 화면입니다"
        Log.e("ScreenBuild", "QuestBadgeFragment")
    }
}