package kr.co.pureum.views.battle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleCompletionBinding
import kr.co.pureum.databinding.FragmentMyBattleProgressBinding


class MyBattleCompletionFragment : BaseFragment<FragmentMyBattleCompletionBinding>(R.layout.fragment_my_battle_completion) {

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