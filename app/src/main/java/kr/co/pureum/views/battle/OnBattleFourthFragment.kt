package kr.co.pureum.views.battle

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBattleFourthBinding

@AndroidEntryPoint
class OnBattleFourthFragment : BaseFragment<FragmentOnBattleFourthBinding>(R.layout.fragment_on_battle_fourth) {
    private lateinit var viewModel: OnBattleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireActivity() as OnBattleActivity).viewModel
    }

    private fun initListener() {

    }

    private fun observe() {

    }
}