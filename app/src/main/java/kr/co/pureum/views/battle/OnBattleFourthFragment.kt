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
        (requireActivity() as OnBattleActivity).changeToolbarColor()
        with(binding) {
            isLoading = false
            keyword = viewModel.keywordLiveData.value
            nickname = "푸름"
            mySentence = viewModel.sentenceLiveData.value
            opponentNickname = viewModel.opponentLiveData.value!!.nickname
            day = 1
        }
    }

    private fun initListener() {
        with(binding) {
            var days = 1
            battlePeriodDecreaseButton.setOnClickListener {
                if (days in 2..10) days -= 1
                day = days
            }
            battlePeriodIncreaseButton.setOnClickListener {
                if (days in 1..9) days += 1
                day = days
            }
        }
    }

    private fun observe() {

    }
}