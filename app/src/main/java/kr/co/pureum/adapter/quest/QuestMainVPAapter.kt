package kr.co.pureum.adapter.quest

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.pureum.views.quest.QuestBadgeFragment
import kr.co.pureum.views.quest.QuestChallengeFragment
import kr.co.pureum.views.quest.QuestFragment

class QuestMainVPAdapter(fragment : QuestFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuestBadgeFragment()
            1 -> QuestChallengeFragment()
            else -> QuestBadgeFragment()
        }
    }

}