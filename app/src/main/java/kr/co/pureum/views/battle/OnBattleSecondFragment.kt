package kr.co.pureum.views.battle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBattleSecondBinding

@AndroidEntryPoint
class OnBattleSecondFragment : BaseFragment<FragmentOnBattleSecondBinding>(R.layout.fragment_on_battle_second) {
    private val viewModel by viewModels<BattleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        with(binding) {
            keyword = viewModel.keywordLiveData.value
        }
    }

    private fun initListener() {

    }

    private fun observe() {

    }
}