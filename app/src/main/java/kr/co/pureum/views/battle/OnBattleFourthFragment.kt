package kr.co.pureum.views.battle

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
        viewModel.getMyProfileImage()
        (requireActivity() as OnBattleActivity).changeToolbarColor()
        with(binding) {
            keyword = viewModel.keywordLiveData.value!!.word
            mySentence = viewModel.sentenceLiveData.value
            opponentNickname = viewModel.opponentLiveData.value!!.nickname
            day = 3
        }
    }

    private var days = 3
    private fun initListener() {
        with(binding) {
            battlePeriodDecreaseButton.setOnClickListener {
                if (days in 4..10) days -= 1
                day = days
            }
            battlePeriodIncreaseButton.setOnClickListener {
                if (days in 3..9) days += 1
                day = days
            }
            battleFinalNextButton.setOnClickListener {
                viewModel.sendBattleRequest(days)

            }
        }
    }

    private fun observe() {
        viewModel.profileImageLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                nickname = it.nickname

                Glide.with(requireContext())
                    .load(it.image)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(userProfileImage1)

                Glide.with(requireContext())
                    .load(it.image)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(userProfileImage2)

                Glide.with(requireContext())
                    .load(viewModel.opponentLiveData.value!!.image)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(opponentProfileImage1)

                Glide.with(requireContext())
                    .load(viewModel.opponentLiveData.value!!.image)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(opponentProfileImage2)
            }
        }
        viewModel.requestLiveData.observe(viewLifecycleOwner) {
            showBattleRequestDialog(viewModel.keywordLiveData.value!!.word, days)
        }
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