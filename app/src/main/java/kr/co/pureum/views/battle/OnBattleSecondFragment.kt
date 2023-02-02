package kr.co.pureum.views.battle

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.databinding.DialogErrorBinding
import kr.co.pureum.databinding.FragmentOnBattleSecondBinding

@AndroidEntryPoint
class OnBattleSecondFragment : BaseFragment<FragmentOnBattleSecondBinding>(R.layout.fragment_on_battle_second) {
    private lateinit var _keyword: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        _keyword = (requireActivity() as OnBattleActivity).viewModel.keywordLiveData.value.toString()
        (requireActivity() as OnBattleActivity).viewModel.getDefinition(_keyword)
        with(binding) {
            keyword = _keyword
        }
    }

    private fun initListener() {
        with(binding) {
            battleSentenceButton.setOnClickListener {
                if (validateSentence(battleSentenceEditText.text.toString()) == "") {
                    requireActivity().supportFragmentManager.beginTransaction()
                } else {
                    showErrorDialog(validateSentence(battleSentenceEditText.text.toString()))
                }
            }
        }
    }

    private fun observe() {
        (requireActivity() as OnBattleActivity).viewModel.definitionLiveData.observe(viewLifecycleOwner) {
            binding.definition = it
        }
    }

    private fun validateSentence(sentence: String): String =
        if (sentence.isNotBlank())
            if (sentence.contains(_keyword)) ""
            else "키워드를 포함하여 작성해주세요."
        else "내용을 입력해주세요."

    private fun showErrorDialog(message: String) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogErrorBinding.inflate(LayoutInflater.from(requireContext()))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding) {
            errorMessage = message
            confirmText = "확인"
            dialogConfirmButton.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }
}