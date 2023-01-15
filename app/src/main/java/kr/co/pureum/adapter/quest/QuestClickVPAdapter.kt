package kr.co.pureum.adapter.quest

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.pureum.views.quest.*

class QuestClickVPAdapter(fragment: QuestClickFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuestWritingBeforeFragment()
            1 -> QuestWritingCompletionFragment()
            else -> QuestWritingBeforeFragment()
        }
    }
}
