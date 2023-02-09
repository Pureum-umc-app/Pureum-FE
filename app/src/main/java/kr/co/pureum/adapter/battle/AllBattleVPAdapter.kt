package kr.co.pureum.adapter.battle

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.pureum.views.battle.AllBattleCompletionFragment
import kr.co.pureum.views.battle.AllBattleFragment
import kr.co.pureum.views.battle.AllBattleProgressFragment
import kr.co.pureum.views.battle.MyBattleCompletionFragment
import kr.co.pureum.views.battle.MyBattleProgressFragment

class AllBattleVPAdapter (fragment: AllBattleFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllBattleProgressFragment()
            1 -> AllBattleCompletionFragment()
            else -> AllBattleProgressFragment()
        }
    }
}