package kr.co.pureum.adapter.quest

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.pureum.views.quest.*


class QuestVoidVPAdapter(fragment: QuestVoidFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuestLatestSentenceFragment()
            1 -> QuestPopularitySentenceFragment()
            else -> QuestPopularitySentenceFragment()
        }
    }
}