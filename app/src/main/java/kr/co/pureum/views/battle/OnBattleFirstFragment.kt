package kr.co.pureum.views.battle

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBattleFirstBinding

@AndroidEntryPoint
class OnBattleFirstFragment : BaseFragment<FragmentOnBattleFirstBinding>(R.layout.fragment_on_battle_first) {
    private var _keywords = listOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        (requireActivity() as OnBattleActivity).viewModel.getThreeKeywords()
        binding.isLoading = true
    }

    private fun initListener() {
        with(binding) {
            battleKeyword1.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    (requireActivity() as OnBattleActivity).viewModel.setKeyword(_keywords[0])
                }
            }
            battleKeyword2.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    (requireActivity() as OnBattleActivity).viewModel.setKeyword(_keywords[1])
                }
            }
            battleKeyword3.setOnClickListener {
                if (_keywords.isNotEmpty()){
                    (requireActivity() as OnBattleActivity).viewModel.setKeyword(_keywords[2])
                }
            }
        }
    }

    private fun observe() {
        (requireActivity() as OnBattleActivity).viewModel.keywordsLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                keywords = it
                _keywords = it
                isLoading = false
                Log.e(TAG, _keywords.toString())
            }
        }
        (requireActivity() as OnBattleActivity).viewModel.keywordLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, it)
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.battle_frame, OnBattleSecondFragment())
                .commit()
        }
    }
}