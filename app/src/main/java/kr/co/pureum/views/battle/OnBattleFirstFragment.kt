package kr.co.pureum.views.battle

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBattleFirstBinding

@AndroidEntryPoint
class OnBattleFirstFragment : BaseFragment<FragmentOnBattleFirstBinding>(R.layout.fragment_on_battle_first) {
    private lateinit var viewModel: OnBattleViewModel
    private var _keywords = listOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireActivity() as OnBattleActivity).viewModel
        viewModel.getThreeKeywords()
        binding.isLoading = true
    }

    private fun initListener() {
        with(binding) {
            battleKeyword1.setOnClickListener { if (_keywords.isNotEmpty()) viewModel.setKeyword(_keywords[0]) }
            battleKeyword2.setOnClickListener { if (_keywords.isNotEmpty()) viewModel.setKeyword(_keywords[1]) }
            battleKeyword3.setOnClickListener { if (_keywords.isNotEmpty()) viewModel.setKeyword(_keywords[2]) }
        }
    }

    private fun observe() {
        viewModel.keywordsLiveData.observe(viewLifecycleOwner) {
            _keywords = it
            Log.e(TAG, _keywords.toString())
            with(binding) {
                keywords = it
                isLoading = false
            }
        }
        viewModel.keywordLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, it)
            (requireActivity() as OnBattleActivity).navigate(OnBattleSecondFragment())
        }
    }
}