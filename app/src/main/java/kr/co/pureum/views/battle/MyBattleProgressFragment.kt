package kr.co.pureum.views.battle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleProgressBinding
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding
import kr.co.pureum.views.quest.QuestClickFragmentDirections


class MyBattleProgressFragment : BaseFragment<FragmentMyBattleProgressBinding>(R.layout.fragment_my_battle_progress) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {

    }

    private fun initListener() {
        with(binding) {

        }
    }

}