package kr.co.pureum.views.battle

import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.databinding.DialogErrorBinding
import kr.co.pureum.databinding.FragmentOnBattleSecondBinding

@AndroidEntryPoint
class OnBattleSecondFragment : BaseFragment<FragmentOnBattleSecondBinding>(R.layout.fragment_on_battle_second) {
    private lateinit var viewModel: OnBattleViewModel
    private var myKeyword = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireActivity() as OnBattleActivity).viewModel
        myKeyword = viewModel.keywordLiveData.value!!.word
        with(binding) {
            keyword = myKeyword
            definition = viewModel.keywordLiveData.value!!.meaning
        }
    }

    private fun initListener() {
        with(binding) {
            battleSentenceEditText.addTextChangedListener {
                battleSentenceClearButton.visibility = when(it.isNullOrEmpty()) {
                    true -> View.GONE
                    else -> View.VISIBLE
                }
            }
            battleSentenceClearButton.setOnClickListener { showConfirmDialog() }
            battleSentenceButton.setOnClickListener {
                val sentence = battleSentenceEditText.text.toString()
                if (validateSentence(sentence) == "") viewModel.setSentence(sentence)
                else showErrorDialog(validateSentence(sentence))
            }
        }
    }

    private fun observe() {
        viewModel.sentenceLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, it)
            (requireActivity() as OnBattleActivity).navigate(OnBattleThirdFragment())
        }
    }

    private fun validateSentence(sentence: String): String =
        if (sentence.isNotBlank())
            if (sentence.contains(myKeyword)) ""
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

    private fun showConfirmDialog() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogDefaultBinding.inflate(LayoutInflater.from(requireContext()))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding) {
            titleText = "작성된 내용이 전부 지워집니다.\n지우시겠습니까?"
            cancelText = "취소"
            confirmText = "지우기"
            dialogCancelButton.setOnClickListener { dialog.dismiss() }
            dialogConfirmButton.setOnClickListener {
                binding.battleSentenceEditText.text.clear()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}