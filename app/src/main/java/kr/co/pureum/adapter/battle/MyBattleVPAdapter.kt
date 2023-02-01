package kr.co.pureum.adapter.battle

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.pureum.views.battle.MyBattleCompletionFragment
import kr.co.pureum.views.battle.MyBattleFragment
import kr.co.pureum.views.battle.MyBattleProgressFragment

class MyBattleVPAdapter(fragment: MyBattleFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyBattleProgressFragment()
            1 -> MyBattleCompletionFragment()
            else -> MyBattleProgressFragment()
        }
    }
}