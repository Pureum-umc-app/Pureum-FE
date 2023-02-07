package kr.co.pureum.views.battle

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.DialogBattleRequestBinding
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.databinding.FragmentOnBattleFourthBinding
import kr.co.pureum.views.MainActivity

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
            battleFinalNextButton.setOnClickListener {
                showBattleRequestDialog(viewModel.keywordLiveData.value!!, days)
            }
        }
    }

    private fun observe() {

    }

    private fun showBattleRequestDialog(_keyword: String, _period: Int) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogBattleRequestBinding.inflate(LayoutInflater.from(requireContext()))
        with(dialogBinding) {
            keyword = _keyword
            period = _period
            battleRequestMyBattleButton.setOnClickListener {
                // TODO: 내 배틀 페이지로 이동 - 현재는 임시로 홈 화면으로 가도록 설정
                val intent = Intent(requireContext(), MainActivity::class.java).apply {
                    putExtra("screen", 1)
                }
                dialog.dismiss()
                startActivity(intent)
                requireActivity().finish()
            }
            battleRequestExitButton.setOnClickListener {
                val intent = Intent(requireContext(), MainActivity::class.java).apply {
                    putExtra("screen", 1)
                }
                dialog.dismiss()
                startActivity(intent)
                requireActivity().finish()
            }
        }
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_main1_20dp)
            setContentView(dialogBinding.root)
            dialog.show()
        }
    }
}